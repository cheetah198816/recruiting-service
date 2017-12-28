package service.application.impl;

import application.ApplicationDto;
import enums.ApplicationStatus;
import events.application.*;
import mappers.application.ApplicationMapper;
import model.ApplicationEntity;
import model.OfferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request.application.SaveApplicationRequest;
import request.application.UpdateApplicationRequest;
import response.application.FetchApplicationResponse;
import response.application.FetchApplicationsResponse;
import response.application.SaveApplicationResponse;
import response.application.UpdateApplicationResponse;
import service.application.ApplicationCommandService;
import service.application.ApplicationQueryService;
import service.application.ApplicationRecruitmentService;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by chetan on 27.12.2017.
 */
@Service
public class ApplicationRecruitmentServiceImpl implements ApplicationRecruitmentService {

    @Autowired
    private ApplicationQueryService applicationQueryService;

    @Autowired
    private ApplicationCommandService applicationCommandService;

    @Override
    public SaveApplicationResponse saveApplication(SaveApplicationRequest saveApplicationRequest, Long offerId) {
        final SaveApplicationResponse saveApplicationResponse = new SaveApplicationResponse();
        final BaseApplicationEvent baseApplicationEvent;
        final UUID offerUuid = applicationQueryService.fetchOfferUuid(offerId);
        //validate the email Id at this step.
        final Optional<ApplicationEntity> applicationEntityOptional = applicationQueryService
                .fetchApplicationByEmailId(offerId, saveApplicationRequest.getEmailId());
        if (!applicationEntityOptional.isPresent()) {
            final UUID applicationUUID = UUID.randomUUID();
            final BaseApplicationEvent event = applicationCommandService
                    .sendApplicationStatusEvent(new ApplicationAppliedEvent(saveApplicationRequest.getEmailId(),
                            saveApplicationRequest.getResume(), offerUuid, applicationUUID, new Date(), ApplicationStatus.APPLIED));
            saveApplicationResponse.setEvent(event);
        } else {
            throw new ValidationException("Application with the same email id already exists.");
        }
        return saveApplicationResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FetchApplicationsResponse getAllApplications(Long offerId) {
        final FetchApplicationsResponse fetchApplicationsResponse = new FetchApplicationsResponse();
        final OfferEntity offerEntity = applicationQueryService.fetchAllApplications(offerId);
        fetchApplicationsResponse.setOfferId(offerEntity.getId());
        fetchApplicationsResponse.setJobTitle(offerEntity.getJobTitle());
        final List<ApplicationDto> applicationDtos = offerEntity.getApplicationEntities().stream()
                .map(applicationEntity -> ApplicationMapper.entity2dto(applicationEntity))
                .collect(Collectors.toList());
        fetchApplicationsResponse.setApplicationDtoList(applicationDtos);
        return fetchApplicationsResponse;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FetchApplicationResponse fetchApplication(Long offerId, Long applicationId) {
        final FetchApplicationResponse fetchApplicationResponse = new FetchApplicationResponse();
        final ApplicationEntity applicationEntity = applicationQueryService.fetchApplication(offerId, applicationId);
        final ApplicationDto applicationDto = ApplicationMapper.entity2dto(applicationEntity);
        fetchApplicationResponse.setApplicationDto(applicationDto);
        return fetchApplicationResponse;
    }

    @Override
    public UpdateApplicationResponse updateApplication(UpdateApplicationRequest updateApplicationRequest, Long offerId, Long applicationId) {
        final UpdateApplicationResponse updateApplicationResponse = new UpdateApplicationResponse();
        final BaseApplicationEvent baseApplicationEvent;
        final ApplicationEntity applicationEntity = applicationQueryService.fetchApplication(offerId, applicationId);
        final UUID applicationUuid = applicationEntity.getApplicationUuid();
        final UUID offerUuid = applicationEntity.getOffer().getOfferUuid();
        switch (updateApplicationRequest.getApplicationStatus()) {
            case HIRED:
                baseApplicationEvent = applicationCommandService
                        .sendApplicationStatusEvent(new ApplicationHiredEvent(offerUuid, applicationUuid, new Date(),
                                updateApplicationRequest.getApplicationStatus()));
                updateApplicationResponse.setEvent(baseApplicationEvent);
                break;
            case INVITED:
                baseApplicationEvent = applicationCommandService
                        .sendApplicationStatusEvent(new ApplicationInvitedEvent(offerUuid, applicationUuid, new Date(),
                                updateApplicationRequest.getApplicationStatus()));
                updateApplicationResponse.setEvent(baseApplicationEvent);
                break;
            case REJECTED:
                baseApplicationEvent = applicationCommandService
                        .sendApplicationStatusEvent(new ApplicationRejectedEvent(offerUuid, applicationUuid, new Date(),
                                updateApplicationRequest.getApplicationStatus()));
                updateApplicationResponse.setEvent(baseApplicationEvent);
                break;
            case APPLIED:
                throw new ValidationException("Cannot set the status to APPLIED.");
        }
        return updateApplicationResponse;
    }
}

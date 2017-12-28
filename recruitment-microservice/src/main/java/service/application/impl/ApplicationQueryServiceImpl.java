package service.application.impl;

import model.ApplicationEntity;
import model.OfferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.application.ApplicationRepository;
import response.application.FetchApplicationsResponse;
import service.application.ApplicationQueryService;
import service.offer.OffersQueryService;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
@Service
public class ApplicationQueryServiceImpl implements ApplicationQueryService {

    @Autowired
    private OffersQueryService offersQueryService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    @Transactional(readOnly = true)
    public OfferEntity fetchAllApplications(Long offerId) {
        final FetchApplicationsResponse fetchApplicationsResponse = new FetchApplicationsResponse();
        final OfferEntity offerEntity = offersQueryService.findById(offerId);
        return offerEntity;
    }

    @Override
    @Transactional(readOnly = true)
    public ApplicationEntity fetchApplication(Long offerId, Long applicationId) {
        final OfferEntity offerEntity = offersQueryService.findById(offerId);
        final ApplicationEntity applicationEntity = applicationRepository.findByIdAndOffer(applicationId, offerEntity)
                .orElseThrow(() -> new EntityNotFoundException("Application not found for the application id " + applicationId));
        return applicationEntity;
    }

    @Override
    public Optional<ApplicationEntity> fetchApplicationByEmailId(Long offerId, String emailId) {
        final OfferEntity offerEntity = offersQueryService.findById(offerId);
        return applicationRepository.findByEmailIdAndOffer(emailId, offerEntity);
    }

    @Override
    public UUID fetchOfferUuid(Long offerId) {
        final OfferEntity offerEntity = offersQueryService.findById(offerId);
        return offerEntity.getOfferUuid();
    }
}

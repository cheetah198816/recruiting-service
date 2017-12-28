package service.offer.impl;

import events.offer.BaseOfferEvent;
import events.offer.CreatedOfferEvent;
import mappers.offer.OfferMapper;
import model.OfferEntity;
import offer.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request.offer.SaveOfferRequest;
import response.offer.FetchOfferByIdResponse;
import response.offer.FetchOffersResponse;
import response.offer.SaveOfferResponse;
import response.offer.TrackApplicationResponse;
import service.offer.OffersCommandService;
import service.offer.OffersQueryService;
import service.offer.OffersRecruitmentService;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Created by chetan on 27.12.2017.
 */
@Service
public class OffersRecruitmentServiceImpl implements OffersRecruitmentService {

    @Autowired
    private OffersQueryService offersQueryService;

    @Autowired
    private OffersCommandService commandService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SaveOfferResponse saveOffer(SaveOfferRequest saveOfferRequest) {
        final SaveOfferResponse saveOfferResponse = new SaveOfferResponse();
        final BaseOfferEvent baseOfferEvent;
        final Date todaysDate = new Date();
        if (saveOfferRequest.getStartDate().getTime() > todaysDate.getTime()) {
            Optional<OfferEntity> offerEntityOptional = offersQueryService.findByJobTitle(saveOfferRequest.getJobTitle());
            if (!offerEntityOptional.isPresent()) {
                final UUID offerUuid = UUID.randomUUID();
                baseOfferEvent = commandService.sendOfferEvent(new CreatedOfferEvent(offerUuid, new Date(), saveOfferRequest.getJobTitle(), saveOfferRequest.getStartDate()));
            } else {
                throw new ValidationException("Offer with same job title already exists.");
            }
        } else {
            throw new ValidationException("Start Date should be greater than current Date");
        }
        saveOfferResponse.setEvent(baseOfferEvent);

        return saveOfferResponse;
    }

    @Override
    public FetchOffersResponse fetchAllOffers() {
        final FetchOffersResponse fetchOffersResponse = new FetchOffersResponse();
        final List<OfferDto> offerDtoList = offersQueryService.findAllOffers();
        fetchOffersResponse.setOfferDtoList(offerDtoList);

        return fetchOffersResponse;
    }

    @Override
    public FetchOfferByIdResponse fetchOfferById(Long offerId) {
        final FetchOfferByIdResponse fetchOfferByIdResponse = new FetchOfferByIdResponse();
        final OfferEntity offerEntity = offersQueryService.findById(offerId);
        final OfferDto offerDto = OfferMapper.entity2Dto(offerEntity);
        fetchOfferByIdResponse.setOfferDto(offerDto);

        return fetchOfferByIdResponse;
    }

    @Override
    public TrackApplicationResponse fetchNumberOfApplications(Long offerId) {
        final TrackApplicationResponse trackApplicationResponse = new TrackApplicationResponse();
        final Integer numberOfApplications = offersQueryService.fetchNumberOfApplications(offerId);
        trackApplicationResponse.setNumberOfApplications(numberOfApplications);

        return trackApplicationResponse;
    }
}

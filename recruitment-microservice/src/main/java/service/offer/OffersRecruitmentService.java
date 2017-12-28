package service.offer;

import request.offer.SaveOfferRequest;
import response.offer.FetchOfferByIdResponse;
import response.offer.FetchOffersResponse;
import response.offer.SaveOfferResponse;
import response.offer.TrackApplicationResponse;

/**
 * Created by chetan on 27.12.2017.
 */
public interface OffersRecruitmentService {

    /**
     * saves the offer request.
     *
     * @param saveOfferRequest dto for saving the offer
     * @return response containing the offer created event.
     */
    SaveOfferResponse saveOffer(SaveOfferRequest saveOfferRequest);

    /**
     * Fetches all the offers.
     *
     * @return dto containing list of all the offers
     */
    FetchOffersResponse fetchAllOffers();

    /**
     * Fetch single offer based on offerID.
     *
     * @param offerId offer Id.
     * @return dto containing the offer with the given offer Id.
     */
    FetchOfferByIdResponse fetchOfferById(Long offerId);

    /**
     * Fetches the number of applications per offer.
     *
     * @param offerId offer Id
     * @return dto containing the number of applications for the given offer id.
     */
    TrackApplicationResponse fetchNumberOfApplications(Long offerId);
}

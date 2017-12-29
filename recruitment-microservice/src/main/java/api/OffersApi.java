package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.offer.SaveOfferRequest;
import response.offer.FetchOfferByIdResponse;
import response.offer.FetchOffersResponse;
import response.offer.SaveOfferResponse;
import response.offer.TrackApplicationResponse;
import service.offer.OffersRecruitmentService;

import javax.validation.Valid;

/**
 * Created by chetan on 27.12.2017.
 */
@RestController
@RequestMapping("/api")
public class OffersApi {

    @Autowired
    private OffersRecruitmentService offersRecruitmentService;

    /**
     * saves the offer.
     *
     * @param saveOfferRequest dto containing the offer details.
     * @return dto containing the offer created event.
     */
    @RequestMapping(value = "/saveOffer", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public SaveOfferResponse saveOffer(@RequestBody @Valid SaveOfferRequest saveOfferRequest) {
        return offersRecruitmentService.saveOffer(saveOfferRequest);
    }

    /**
     * Fetches all the offers
     *
     * @return dto containg the list of all the offers.
     */
    @RequestMapping(value = "/fetchOffers", produces = "application/json", method = RequestMethod.GET)
    public FetchOffersResponse fetchAllOffers() {
        return offersRecruitmentService.fetchAllOffers();
    }

    /**
     * Fetch a single offer.
     *
     * @param offerId offerId
     * @return dto containing the offer for the given offer Id.
     */
    @RequestMapping(value = "/fetchOffer/{offerId}", produces = "application/json", method = RequestMethod.GET)
    public FetchOfferByIdResponse fetchOfferById(@PathVariable("offerId") Long offerId) {
        return offersRecruitmentService.fetchOfferById(offerId);
    }

    /**
     * Fetches the number of applications per offer.
     *
     * @param offerId offerId.
     * @return dto containing the number of applications for the given offer Id.
     */
    @RequestMapping(value = "/trackApplications/{offerId}", produces = "application/json", method = RequestMethod.GET)
    public TrackApplicationResponse trackApplicationPerOffer(@PathVariable("offerId") Long offerId) {
        return offersRecruitmentService.fetchNumberOfApplications(offerId);
    }
}

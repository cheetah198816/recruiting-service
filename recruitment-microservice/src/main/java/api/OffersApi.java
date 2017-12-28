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

    @RequestMapping(value = "/saveOffer", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public SaveOfferResponse saveOffer(@RequestBody @Valid SaveOfferRequest saveOfferRequest) {
        return offersRecruitmentService.saveOffer(saveOfferRequest);
    }

    @RequestMapping(value = "/fetchOffers", produces = "application/json", method = RequestMethod.GET)
    public FetchOffersResponse fetchAllOffers() {
        return offersRecruitmentService.fetchAllOffers();
    }

    @RequestMapping(value = "/fetchOffer/{offerId}", produces = "application/json", method = RequestMethod.GET)
    public FetchOfferByIdResponse fetchOfferById(@PathVariable("offerId") Long offerId) {
        return offersRecruitmentService.fetchOfferById(offerId);
    }

    @RequestMapping(value = "/trackApplications/{offerId}", produces = "application/json", method = RequestMethod.GET)
    public TrackApplicationResponse trackApplicationPerOffer(@PathVariable("offerId") Long offerId) {
        return offersRecruitmentService.fetchNumberOfApplications(offerId);
    }


}

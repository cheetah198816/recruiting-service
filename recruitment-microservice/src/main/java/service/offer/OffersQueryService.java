package service.offer;

import model.OfferEntity;
import offer.OfferDto;

import java.util.List;
import java.util.Optional;

/**
 * Created by chetan on 27.12.2017.
 */
public interface OffersQueryService {

    /**
     * Finds all the offers.
     *
     * @return list of all the offers.
     */
    List<OfferDto> findAllOffers();

    /**
     * Finds the offer by offer Id.
     *
     * @param offerId offer Id.
     * @return Offer with given offer id.
     */
    OfferEntity findById(Long offerId);

    /**
     * Finds the offer by job title
     *
     * @param jobTitle job Title
     * @return offer with the given job title
     */
    Optional<OfferEntity> findByJobTitle(String jobTitle);

    /**
     * Fetches the number of applications per offer.
     *
     * @param offerId offer Id
     * @return number of applications for the given offer id.
     */
    Integer fetchNumberOfApplications(Long offerId);
}

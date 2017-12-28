package service.application;

import model.ApplicationEntity;
import model.OfferEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
public interface ApplicationQueryService {

    /**
     * Fetches all the applications per offer
     *
     * @param offerId offer Id
     * @return Offer Entity containing a list of all applications.
     */
    OfferEntity fetchAllApplications(Long offerId);

    /**
     * Fetches the single application per offer.
     *
     * @param offerId       offerId.
     * @param applicationId applicationId.
     * @return application entity for the given offer Id and application Id.
     */
    ApplicationEntity fetchApplication(Long offerId, Long applicationId);

    /**
     * Fetches the application by email id.
     *
     * @param offerId offer Id.
     * @param emailId email Id.
     * @return application entity for the given email id.
     */
    Optional<ApplicationEntity> fetchApplicationByEmailId(Long offerId, String emailId);

    /**
     * Fetches the offer UUID for the given offer ID.
     *
     * @param offerId offer ID.
     * @return Offer UUID.
     */
    UUID fetchOfferUuid(Long offerId);
}

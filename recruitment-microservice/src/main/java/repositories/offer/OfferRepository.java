package repositories.offer;

import model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    Optional<OfferEntity> findById(Long offerId);

    Optional<OfferEntity> findByOfferUuid(UUID offerUuid);

    Optional<OfferEntity> findByJobTitle(String jobTitle);
}

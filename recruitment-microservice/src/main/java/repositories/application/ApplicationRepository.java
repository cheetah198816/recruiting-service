package repositories.application;

import model.ApplicationEntity;
import model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    Optional<ApplicationEntity> findByIdAndOffer(Long id, OfferEntity offerEntity);

    Optional<ApplicationEntity> findByEmailIdAndOffer(String emailId, OfferEntity offerEntity);

    Optional<ApplicationEntity> findByApplicationUuidAndOffer(UUID applicationUuid, OfferEntity offerEntity);
}

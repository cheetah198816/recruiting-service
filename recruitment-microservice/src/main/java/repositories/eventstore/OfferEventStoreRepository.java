package repositories.eventstore;

import model.RawOfferEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chetan on 28.12.2017.
 */
@Repository
public interface OfferEventStoreRepository extends JpaRepository<RawOfferEventEntity, Long> {
}

package repositories.eventstore;

import model.RawApplicationEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chetan on 27.12.2017.
 */
@Repository
public interface ApplicationEventStoreRepository extends JpaRepository<RawApplicationEventEntity, Long> {
}

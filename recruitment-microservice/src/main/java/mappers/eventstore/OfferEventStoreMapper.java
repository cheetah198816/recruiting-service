package mappers.eventstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.offer.BaseOfferEvent;
import model.RawOfferEventEntity;

/**
 * Created by chetan on 28.12.2017.
 */
public class OfferEventStoreMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static RawOfferEventEntity event2entity(BaseOfferEvent baseApplicationEvent) {
        final RawOfferEventEntity rawOfferEventEntity = new RawOfferEventEntity();
        try {
            rawOfferEventEntity.setOfferUuid(baseApplicationEvent.getOfferUuid());
            rawOfferEventEntity.setPayload(objectMapper.writeValueAsString(baseApplicationEvent));
            rawOfferEventEntity.setName(baseApplicationEvent.getClass().getCanonicalName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return rawOfferEventEntity;
    }
}

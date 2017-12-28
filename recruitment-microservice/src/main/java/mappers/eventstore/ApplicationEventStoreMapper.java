package mappers.eventstore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.application.BaseApplicationEvent;
import model.RawApplicationEventEntity;

/**
 * Created by chetan on 27.12.2017.
 */
public class ApplicationEventStoreMapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static RawApplicationEventEntity event2entity(BaseApplicationEvent baseApplicationEvent) {
        final RawApplicationEventEntity rawApplicationEventEntity = new RawApplicationEventEntity();
        try {
            rawApplicationEventEntity.setApplicationUuid(baseApplicationEvent.getOfferUuid());
            rawApplicationEventEntity.setApplicationUuid(baseApplicationEvent.getApplicationUuid());
            rawApplicationEventEntity.setApplicationStatus(baseApplicationEvent.getApplicationStatus());
            rawApplicationEventEntity.setCreationDate(baseApplicationEvent.getCreationDate());
            rawApplicationEventEntity.setPayload(objectMapper.writeValueAsString(baseApplicationEvent));
            rawApplicationEventEntity.setName(baseApplicationEvent.getClass().getCanonicalName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return rawApplicationEventEntity;
    }
}

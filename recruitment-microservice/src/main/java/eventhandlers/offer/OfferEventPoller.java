package eventhandlers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.offer.BaseOfferEvent;
import model.RawOfferEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import repositories.eventstore.OfferEventStoreRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by chetan on 28.12.2017.
 */
@Component
public class OfferEventPoller {

    private ObjectMapper mapper;

    private Long lastOrderProcessedId;

    @Autowired
    private OfferEventStoreRepository offerEventStoreRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedRate = 1000)
    void readNewOfferEvents() {
        offerEventStoreRepository.findAll().stream().filter(ev -> (ev.getId() > lastOrderProcessedId)).forEach(e -> processEvent(e));
    }

    private final void processEvent(final RawOfferEventEntity rawOfferEventEntity) {
        BaseOfferEvent event = null;
        try {
            event = (BaseOfferEvent) mapper.readValue(rawOfferEventEntity.getPayload(), Class.forName(rawOfferEventEntity.getName()));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        lastOrderProcessedId = rawOfferEventEntity.getId();
        eventPublisher.publishEvent(event);
    }

    @PostConstruct
    void instantiate() {
        this.mapper = new ObjectMapper();
        this.lastOrderProcessedId = -1L;
    }
}

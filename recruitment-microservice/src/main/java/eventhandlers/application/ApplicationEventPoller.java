package eventhandlers.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import events.application.BaseApplicationEvent;
import model.RawApplicationEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.eventstore.ApplicationEventStoreRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by chetan on 27.12.2017.
 */
@Component
public class ApplicationEventPoller {

    private ObjectMapper mapper;

    private Long lastProcessedId;

    @Autowired
    private ApplicationEventStoreRepository applicationEventStoreRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Scheduled(fixedRate = 1000)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void readNewOfferEvents() {
        applicationEventStoreRepository.findAll().stream().filter(ev -> (ev.getId() > lastProcessedId)).forEach(e -> processEvent(e));
    }

    private final void processEvent(final RawApplicationEventEntity rawApplicationEventEntity) {
        BaseApplicationEvent event = null;
        try {
            event = (BaseApplicationEvent) mapper.readValue(rawApplicationEventEntity.getPayload(), Class.forName(rawApplicationEventEntity.getName()));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        lastProcessedId = rawApplicationEventEntity.getId();
        eventPublisher.publishEvent(event);
    }

    @PostConstruct
    void instantiate() {
        this.mapper = new ObjectMapper();
        this.lastProcessedId = -1L;
    }
}

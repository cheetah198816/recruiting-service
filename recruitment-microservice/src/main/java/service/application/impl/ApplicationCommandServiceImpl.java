package service.application.impl;

import events.application.BaseApplicationEvent;
import mappers.eventstore.ApplicationEventStoreMapper;
import model.RawApplicationEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.eventstore.ApplicationEventStoreRepository;
import service.application.ApplicationCommandService;

/**
 * Created by chetan on 28.12.2017.
 */
@Service
public class ApplicationCommandServiceImpl implements ApplicationCommandService {

    @Autowired
    private ApplicationEventStoreRepository applicationEventStoreRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BaseApplicationEvent sendApplicationStatusEvent(BaseApplicationEvent baseApplicationEvent) {
        final RawApplicationEventEntity rawApplicationEventEntity = ApplicationEventStoreMapper.event2entity(baseApplicationEvent);
        applicationEventStoreRepository.save(rawApplicationEventEntity);

        return baseApplicationEvent;
    }
}

package service.offer.impl;

import events.offer.BaseOfferEvent;
import mappers.eventstore.OfferEventStoreMapper;
import model.RawOfferEventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.eventstore.OfferEventStoreRepository;
import service.offer.OffersCommandService;

/**
 * Created by chetan on 27.12.2017.
 */
@Service
public class OffersCommandServiceImpl implements OffersCommandService {

    @Autowired
    private OfferEventStoreRepository offerEventStoreRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BaseOfferEvent sendOfferEvent(BaseOfferEvent baseOfferEvent) {
        RawOfferEventEntity rawOfferEventEntity = OfferEventStoreMapper.event2entity(baseOfferEvent);
        offerEventStoreRepository.save(rawOfferEventEntity);

        return baseOfferEvent;
    }
}

package service.offer;

import events.offer.BaseOfferEvent;

/**
 * Created by chetan on 27.12.2017.
 */
public interface OffersCommandService {

    /**
     * Sends the offer event to the event store.
     *
     * @param baseOfferEvent offer event
     * @return offer event saved.
     */
    BaseOfferEvent sendOfferEvent(BaseOfferEvent baseOfferEvent);
}

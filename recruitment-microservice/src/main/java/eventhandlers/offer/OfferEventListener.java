package eventhandlers.offer;

import events.offer.CreatedOfferEvent;
import model.OfferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import repositories.offer.OfferRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by chetan on 28.12.2017.
 */
@Service
public class OfferEventListener {

    private static Logger logger = Logger.getLogger("Exception");

    @Autowired
    private OfferRepository offerRepository;

    @EventListener
    public void onCreateOffer(CreatedOfferEvent createdOfferEvent) {
        try {
            final OfferEntity offerEntity = new OfferEntity();
            offerEntity.setJobTitle(createdOfferEvent.getJobTitle());
            offerEntity.setNoOfApplications(0);
            offerEntity.setStartDate(createdOfferEvent.getStartDate());
            offerEntity.setOfferUuid(createdOfferEvent.getOfferUuid());
            offerRepository.save(offerEntity);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage());
        }
    }
}

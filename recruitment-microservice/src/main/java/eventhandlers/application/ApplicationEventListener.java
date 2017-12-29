package eventhandlers.application;

import events.application.*;
import model.ApplicationEntity;
import model.OfferEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repositories.application.ApplicationRepository;
import repositories.offer.OfferRepository;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by chetan on 27.12.2017.
 */

@Service
public class ApplicationEventListener {

    private static Logger logger = Logger.getLogger("Notification");

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onApplicationApplied(ApplicationAppliedEvent applicationAppliedEvent) {
        final Optional<OfferEntity> optionalOfferEntity = offerRepository.findByOfferUuid(applicationAppliedEvent.getOfferUuid());
        if (optionalOfferEntity.isPresent()) {
            final OfferEntity offerEntity = optionalOfferEntity.get();
            final Integer noOfApplications = offerEntity.getNoOfApplications() + 1;
            offerEntity.setNoOfApplications(noOfApplications);
            final OfferEntity savedOfferEntity = offerRepository.save(offerEntity);
            final ApplicationEntity applicationEntity = new ApplicationEntity();
            applicationEntity.setResume(applicationAppliedEvent.getResume());
            applicationEntity.setApplicationUuid(applicationAppliedEvent.getApplicationUuid());
            applicationEntity.setEmailId(applicationAppliedEvent.getEmailId());
            applicationEntity.setCurrentApplicationStatus(applicationAppliedEvent.getApplicationStatus());
            applicationEntity.setOffer(savedOfferEntity);
            ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
            logger.info("You have applied for the position " + savedOfferEntity.getJobTitle() + " and your application Id is " + savedApplicationEntity.getId());
        }
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onApplicationInvited(ApplicationInvitedEvent applicationInvitedEvent) {
        final String message = "You are invited for the interview ";
        updateApplicationStatusAndSendNotification(applicationInvitedEvent, message);
    }

    @Transactional(readOnly = true)
    private void updateApplicationStatusAndSendNotification(BaseApplicationEvent applicationInvitedEvent, String message) {
        final Optional<OfferEntity> offerEntityOptional = offerRepository.findByOfferUuid(applicationInvitedEvent.getOfferUuid());
        if (offerEntityOptional.isPresent()) {
            final OfferEntity offerEntity = offerEntityOptional.get();
            final Optional<ApplicationEntity> applicationEntityOptional = applicationRepository.findByApplicationUuidAndOffer(applicationInvitedEvent.getApplicationUuid(), offerEntity);
            if (applicationEntityOptional.isPresent()) {
                final ApplicationEntity applicationEntity = applicationEntityOptional.get();
                applicationEntity.setCurrentApplicationStatus(applicationInvitedEvent.getApplicationStatus());
                applicationRepository.save(applicationEntity);
                logger.info(message + ": application Id " + applicationEntity.getId());
            }
        }
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onApplicationHired(ApplicationHiredEvent applicationHiredEvent) {
        final String message = "You are hired.Congratulations!!! ";
        updateApplicationStatusAndSendNotification(applicationHiredEvent, message);
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onApplicationRejected(ApplicationRejectedEvent applicationRejectedEvent) {
        final String message = "Sorry!!You are rejected this time. ";
        updateApplicationStatusAndSendNotification(applicationRejectedEvent, message);
    }
}

import enums.ApplicationStatus;
import model.ApplicationEntity;
import model.OfferEntity;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 29.12.2017.
 */
public class TestHelper {

    public static OfferEntity getSampleOfferEntity(String jobTitle) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        OfferEntity offerEntity = new OfferEntity();
        UUID offerUuid = UUID.randomUUID();
        offerEntity.setOfferUuid(offerUuid);
        offerEntity.setJobTitle(jobTitle);
        offerEntity.setStartDate(dt);
        offerEntity.setNoOfApplications(0);

        return offerEntity;
    }

    public static ApplicationEntity getSampleApplicationEntity(OfferEntity offerEntity, String emailId) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setResume("test1234");
        UUID applicationUuid = UUID.randomUUID();
        applicationEntity.setApplicationUuid(applicationUuid);
        applicationEntity.setOffer(offerEntity);
        applicationEntity.setEmailId(emailId);
        applicationEntity.setCurrentApplicationStatus(ApplicationStatus.APPLIED);

        return applicationEntity;
    }
}

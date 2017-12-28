package events.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import enums.ApplicationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@NoArgsConstructor
@JsonClassDescription("Event for the application hired")
public class ApplicationHiredEvent extends BaseApplicationEvent {
    //add extra fields related to hiring
    public ApplicationHiredEvent(UUID offerUuid, UUID applicationUuid, Date creationDate, ApplicationStatus applicationStatus) {
        super(offerUuid, applicationUuid, creationDate, applicationStatus);
    }

}

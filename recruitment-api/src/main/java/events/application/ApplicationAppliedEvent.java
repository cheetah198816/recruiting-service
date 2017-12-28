package events.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import enums.ApplicationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@NoArgsConstructor
@JsonClassDescription("Event for the application applied")
public class ApplicationAppliedEvent extends BaseApplicationEvent {
    //add extra fields regarding the applied event.
    @JsonPropertyDescription("Email Id")
    @NonNull
    private String emailId;

    @JsonPropertyDescription("Resume")
    @NonNull
    private String resume;

    public ApplicationAppliedEvent(String emailId, String resume, UUID offerUuid, UUID applicationUuid, Date creationDate, ApplicationStatus applicationStatus) {
        super(offerUuid, applicationUuid, creationDate, applicationStatus);
        this.emailId = emailId;
        this.resume = resume;
    }
}

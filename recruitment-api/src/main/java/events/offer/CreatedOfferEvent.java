package events.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@NoArgsConstructor
@JsonClassDescription("Event for the offer created.")
public class CreatedOfferEvent extends BaseOfferEvent {

    @NonNull
    @JsonPropertyDescription("Job Title")
    private String jobTitle;

    @NonNull
    @JsonPropertyDescription("Start Date")
    private Date startDate;

    public CreatedOfferEvent(UUID offerId, Date creationDate, String jobTitle, Date startDate) {
        super(offerId, creationDate);
        this.jobTitle = jobTitle;
        this.startDate = startDate;
    }
}

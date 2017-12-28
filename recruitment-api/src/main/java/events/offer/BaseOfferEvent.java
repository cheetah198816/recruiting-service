package events.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonClassDescription("Base of all offer events.")
public class BaseOfferEvent {

    @NonNull
    @JsonPropertyDescription("Offer UUID")
    private UUID offerUuid;

    @NonNull
    @JsonPropertyDescription("Creation Date")
    private Date creationDate;
}

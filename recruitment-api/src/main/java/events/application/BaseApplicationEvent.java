package events.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import enums.ApplicationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonClassDescription("Base of all application events.")
public abstract class BaseApplicationEvent {

    @NonNull
    @JsonPropertyDescription("Offer UUID")
    private UUID offerUuid;

    @NonNull
    @JsonPropertyDescription("Application UUID")
    private UUID applicationUuid;

    @NonNull
    @JsonPropertyDescription("Creation Date")
    private Date creationDate;

    @NonNull
    @JsonPropertyDescription("Current Application Status")
    private ApplicationStatus applicationStatus;
}

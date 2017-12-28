package request.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import enums.ApplicationStatus;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonClassDescription("Dto to update the application status")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateApplicationRequest {

    @JsonPropertyDescription("Application Status")
    @NonNull
    private ApplicationStatus applicationStatus;
}

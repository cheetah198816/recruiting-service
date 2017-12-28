package request.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@JsonClassDescription("Request Dto for saving the application.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveApplicationRequest {

    @NonNull
    @JsonPropertyDescription("Email Id.")
    private String emailId;

    @NonNull
    @JsonPropertyDescription("Resume")
    private String resume;
}

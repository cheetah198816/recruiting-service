package application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import enums.ApplicationStatus;
import lombok.Data;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription("Dto for the application")
public class ApplicationDto {

    @JsonPropertyDescription("Application Id")
    private Long applicationId;

    @JsonPropertyDescription("Email Id")
    private String emailId;

    @JsonPropertyDescription("Resume")
    private String resume;

    @JsonPropertyDescription("Current Application Status.")
    private ApplicationStatus currentApplicationStatus;
}

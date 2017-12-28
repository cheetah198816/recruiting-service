package response.application;

import application.ApplicationDto;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

import java.util.List;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonClassDescription("Fetch Applications per Offer")
public class FetchApplicationsResponse {

    @JsonPropertyDescription("Offer Id")
    private Long offerId;

    @JsonPropertyDescription("Job Title")
    private String jobTitle;

    @JsonPropertyDescription("List of applications per offer")
    private List<ApplicationDto> applicationDtoList;
}

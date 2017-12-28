package response.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonClassDescription("Dto which fetches the number of applications per offer")
public class TrackApplicationResponse {

    @JsonPropertyDescription("Number Of Applications per Offer")
    private Integer numberOfApplications;
}

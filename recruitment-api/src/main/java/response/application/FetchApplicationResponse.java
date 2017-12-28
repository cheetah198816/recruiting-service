package response.application;

import application.ApplicationDto;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonClassDescription("Dto for fetching the application per offer")
public class FetchApplicationResponse {

    @JsonPropertyDescription("Application Dto")
    private ApplicationDto applicationDto;
}

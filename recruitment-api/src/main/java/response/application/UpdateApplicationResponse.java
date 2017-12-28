package response.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import events.application.BaseApplicationEvent;
import lombok.Data;

/**
 * Created by chetan on 28.12.2017.
 */
@Data
@JsonClassDescription("Response after updating the application.")
public class UpdateApplicationResponse {

    @JsonPropertyDescription("Application Event")
    private BaseApplicationEvent event;
}

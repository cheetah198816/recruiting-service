package response.application;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import events.application.BaseApplicationEvent;
import lombok.Data;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@JsonClassDescription("Response after saving an application.")
public class SaveApplicationResponse {

    @JsonPropertyDescription("Application Event")
    private BaseApplicationEvent event;
}

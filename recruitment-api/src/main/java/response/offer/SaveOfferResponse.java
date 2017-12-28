package response.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import events.offer.BaseOfferEvent;
import lombok.Data;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@JsonClassDescription("Dto after saving the offer")
public class SaveOfferResponse {

    @JsonPropertyDescription("Offer Event")
    private BaseOfferEvent event;
}

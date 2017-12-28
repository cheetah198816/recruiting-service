package response.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import offer.OfferDto;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@JsonClassDescription("Dto to fetch offer by id")
public class FetchOfferByIdResponse {

    @JsonPropertyDescription("Offer Dto")
    private OfferDto offerDto;
}

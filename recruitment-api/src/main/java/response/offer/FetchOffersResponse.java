package response.offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;
import offer.OfferDto;

import java.util.List;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@JsonClassDescription("Dto to fetch all offers")
public class FetchOffersResponse {

    @JsonPropertyDescription("List of all offers")
    private List<OfferDto> offerDtoList;
}

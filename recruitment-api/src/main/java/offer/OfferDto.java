package offer;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

/**
 * Created by chetan on 27.12.2017.
 */
@Data
@NoArgsConstructor
@JsonClassDescription("Offer Dto")
public class OfferDto {

    @NonNull
    private Long id;

    @NonNull
    private String jobTitle;

    @NonNull
    private Integer noOfApplications;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
}

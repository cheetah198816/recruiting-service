package mappers.offer;

import model.OfferEntity;
import offer.OfferDto;

/**
 * Created by chetan on 27.12.2017.
 */
public class OfferMapper {

    public static OfferDto entity2Dto(OfferEntity offerEntity) {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(offerEntity.getId());
        offerDto.setJobTitle(offerEntity.getJobTitle());
        offerDto.setNoOfApplications(offerEntity.getNoOfApplications());
        offerDto.setStartDate(offerEntity.getStartDate());

        return offerDto;
    }
}

package service.offer.impl;

import mappers.offer.OfferMapper;
import model.OfferEntity;
import offer.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.offer.OfferRepository;
import service.offer.OffersQueryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by chetan on 27.12.2017.
 */
@Service
public class OffersQueryServiceImpl implements OffersQueryService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    @Transactional(readOnly = true)
    public OfferEntity findById(Long offerId) {
        return offerRepository.findById(offerId).orElseThrow(() -> new EntityNotFoundException("Offer Entity not found for the offer id " + offerId));
    }

    @Override
    public Optional<OfferEntity> findByJobTitle(String jobTitle) {
        return offerRepository.findByJobTitle(jobTitle);
    }

    @Override
    public Integer fetchNumberOfApplications(Long offerId) {
        final OfferEntity offerEntity = findById(offerId);
        return offerEntity.getNoOfApplications();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDto> findAllOffers() {
        return offerRepository.findAll().stream()
                .map(offerEntity -> OfferMapper.entity2Dto(offerEntity))
                .collect(Collectors.toList());
    }
}

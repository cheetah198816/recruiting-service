
import model.OfferEntity;
import offer.OfferDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repositories.offer.OfferRepository;
import request.offer.SaveOfferRequest;
import response.offer.FetchOfferByIdResponse;
import response.offer.FetchOffersResponse;
import response.offer.SaveOfferResponse;
import service.offer.OffersRecruitmentService;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by chetan on 29.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RecruitmentApplication.class)
public class OfferIntegrationTest {

    @Autowired
    private OffersRecruitmentService offersRecruitmentService;

    @Autowired
    private OfferRepository offerRepository;

    @Before
    public void init() {
        OfferEntity offerEntity = TestHelper.getSampleOfferEntity("Software Engineer" + new Date().getTime());
        offerRepository.save(offerEntity);
    }

    @Test
    public void test_save_offer() throws InterruptedException {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SaveOfferRequest saveOfferRequest = new SaveOfferRequest();
        saveOfferRequest.setJobTitle("Software Engineer" + dt.getTime());
        saveOfferRequest.setStartDate(dt);
        SaveOfferResponse saveOfferResponse = offersRecruitmentService.saveOffer(saveOfferRequest);
        Thread.sleep(1000);
        OfferEntity offerEntity = offerRepository.findByOfferUuid(saveOfferResponse.getEvent().getOfferUuid()).get();

        assertThat(offerEntity).isNotNull();
    }

    @Test(expected = Exception.class)
    public void test_save_offer_start_date_validation() {
        SaveOfferRequest saveOfferRequest = new SaveOfferRequest();
        saveOfferRequest.setJobTitle("Software Engineer");
        saveOfferRequest.setStartDate(new Date());
        offersRecruitmentService.saveOffer(saveOfferRequest);
    }

    @Test(expected = Exception.class)
    public void test_save_offer_duplicate_job_title() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        OfferEntity offerEntity = TestHelper.getSampleOfferEntity("Software Engineer");
        offerRepository.save(offerEntity);
        SaveOfferRequest saveOfferRequestSameJobTitle = new SaveOfferRequest();
        saveOfferRequestSameJobTitle.setJobTitle("Software Engineer");
        saveOfferRequestSameJobTitle.setStartDate(dt);
        offersRecruitmentService.saveOffer(saveOfferRequestSameJobTitle);
    }

    @Test
    public void test_get_offers() {
        FetchOffersResponse fetchOffersResponse = offersRecruitmentService.fetchAllOffers();

        assertThat(fetchOffersResponse.getOfferDtoList()).isNotEmpty();
    }

    @Test
    public void test_get_offer_by_id() {
        FetchOffersResponse fetchOffersResponse = offersRecruitmentService.fetchAllOffers();
        OfferDto offerDto = fetchOffersResponse.getOfferDtoList().get(0);
        FetchOfferByIdResponse fetchOfferByIdResponse = offersRecruitmentService.fetchOfferById(offerDto.getId());

        assertThat(fetchOfferByIdResponse.getOfferDto()).isNotNull();
    }
}

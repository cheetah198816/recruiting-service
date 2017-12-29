import enums.ApplicationStatus;
import model.ApplicationEntity;
import model.OfferEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import repositories.application.ApplicationRepository;
import repositories.offer.OfferRepository;
import request.application.SaveApplicationRequest;
import request.application.UpdateApplicationRequest;
import response.application.FetchApplicationResponse;
import response.application.FetchApplicationsResponse;
import response.application.SaveApplicationResponse;
import response.application.UpdateApplicationResponse;
import response.offer.TrackApplicationResponse;
import service.application.ApplicationRecruitmentService;
import service.offer.OffersRecruitmentService;

import java.util.Date;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by chetan on 29.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RecruitmentApplication.class)
public class ApplicationIntegrationTest {

    @Autowired
    ApplicationRecruitmentService applicationRecruitmentService;

    @Autowired
    OffersRecruitmentService offersRecruitmentService;

    private Long offerId;

    private Long applicationId;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Before
    public void init() {
        OfferEntity offerEntity = TestHelper.getSampleOfferEntity("Software Developer" + new Date().getTime());
        OfferEntity saveOfferEntity = offerRepository.save(offerEntity);
        offerId = saveOfferEntity.getId();
        ApplicationEntity applicationEntity = TestHelper.getSampleApplicationEntity(saveOfferEntity, "abcd1234" + new Date().getTime());
        ApplicationEntity savedApplicationEntity = applicationRepository.save(applicationEntity);
        applicationId = savedApplicationEntity.getId();
    }

    @Test
    public void test_save_application() throws InterruptedException {
        SaveApplicationRequest saveApplicationRequest = new SaveApplicationRequest();
        saveApplicationRequest.setResume("abcd1234");
        saveApplicationRequest.setEmailId("cnaik011@gmail.com");
        SaveApplicationResponse saveApplicationResponse = applicationRecruitmentService.saveApplication(saveApplicationRequest, offerId);
        assertThat(saveApplicationResponse.getEvent().getApplicationUuid()).isNotNull();
        Thread.sleep(1000);

        assertThat(applicationRepository.findByApplicationUuidAndOffer(saveApplicationResponse.getEvent().getApplicationUuid(), offerRepository.findById(offerId).get())).isNotNull();
    }

    @Test(expected = Exception.class)
    public void test_save_application_duplicate_email() throws InterruptedException {
        SaveApplicationRequest saveApplicationRequest = new SaveApplicationRequest();
        saveApplicationRequest.setResume("abcd1234");
        saveApplicationRequest.setEmailId("cnaik011@gmail.com2");
        SaveApplicationResponse saveApplicationResponse = applicationRecruitmentService.saveApplication(saveApplicationRequest, offerId);
        assertThat(saveApplicationResponse.getEvent().getApplicationUuid()).isNotNull();
        Thread.sleep(1000);
        SaveApplicationRequest saveApplicationRequestDuplicateEmail = new SaveApplicationRequest();
        saveApplicationRequestDuplicateEmail.setResume("abcd1234");
        saveApplicationRequestDuplicateEmail.setEmailId("cnaik011@gmail.com2");
        applicationRecruitmentService.saveApplication(saveApplicationRequestDuplicateEmail, offerId);
    }

    @Test
    public void test_get_all_applications_per_offer() {
        FetchApplicationsResponse fetchApplicationsResponse = applicationRecruitmentService.getAllApplications(offerId);

        assertThat(fetchApplicationsResponse.getApplicationDtoList()).isNotEmpty();
    }

    @Test
    public void test_get_application_per_offer() {
        FetchApplicationResponse fetchApplicationResponse = applicationRecruitmentService.fetchApplication(offerId, applicationId);

        assertThat(fetchApplicationResponse.getApplicationDto()).isNotNull();
    }

    @Test
    public void test_update_application() throws InterruptedException {
        UpdateApplicationRequest updateApplicationRequest = new UpdateApplicationRequest();
        updateApplicationRequest.setApplicationStatus(ApplicationStatus.HIRED);
        UpdateApplicationResponse updateApplicationResponse = applicationRecruitmentService.updateApplication(updateApplicationRequest, offerId, applicationId);

        assertThat(updateApplicationResponse.getEvent().getApplicationUuid()).isNotNull();

        Thread.sleep(2000);

        OfferEntity offerEntity = offerRepository.findById(offerId).get();
        ApplicationEntity applicationEntity = applicationRepository.findByApplicationUuidAndOffer(updateApplicationResponse.getEvent().getApplicationUuid(), offerEntity).get();

        assertThat(applicationEntity.getCurrentApplicationStatus()).isEqualTo(ApplicationStatus.HIRED);
    }

    @Test
    public void track_applications_test() throws InterruptedException {
        SaveApplicationRequest saveApplicationRequest = new SaveApplicationRequest();
        saveApplicationRequest.setResume("abcd1234");
        saveApplicationRequest.setEmailId("cnaik011@gmail.com1");
        SaveApplicationResponse saveApplicationResponse = applicationRecruitmentService.saveApplication(saveApplicationRequest, offerId);
        assertThat(saveApplicationResponse.getEvent().getApplicationUuid()).isNotNull();
        Thread.sleep(1000);
        TrackApplicationResponse trackApplicationResponse = offersRecruitmentService.fetchNumberOfApplications(offerId);

        assertThat(trackApplicationResponse.getNumberOfApplications()).isEqualTo(1);
    }


}

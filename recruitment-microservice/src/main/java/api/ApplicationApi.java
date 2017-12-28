package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import request.application.SaveApplicationRequest;
import request.application.UpdateApplicationRequest;
import response.application.FetchApplicationResponse;
import response.application.FetchApplicationsResponse;
import response.application.SaveApplicationResponse;
import response.application.UpdateApplicationResponse;
import service.application.ApplicationRecruitmentService;

import javax.validation.Valid;

/**
 * Created by chetan on 27.12.2017.
 */
@RestController
@RequestMapping("/api")
public class ApplicationApi {

    @Autowired
    private ApplicationRecruitmentService applicationRecruitmentService;


    @RequestMapping(value = "/saveApplication/{offerId}", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    public SaveApplicationResponse saveApplication(@RequestBody @Valid SaveApplicationRequest saveApplicationRequest, @PathVariable("offerId") Long offerId) {
        return applicationRecruitmentService.saveApplication(saveApplicationRequest, offerId);
    }

    @RequestMapping(value = "/fetchApplications/{offerId}", produces = "application/json", method = RequestMethod.GET)
    public FetchApplicationsResponse getAllApplications(@PathVariable("offerId") Long offerId) {
        return applicationRecruitmentService.getAllApplications(offerId);
    }

    @RequestMapping(value = "/fetchApplication/{offerId}/{applicationId}", produces = "application/json", method = RequestMethod.GET)
    public FetchApplicationResponse fetchApplication(@PathVariable("offerId") Long offerId, @PathVariable("applicationId") Long applicationId) {
        return applicationRecruitmentService.fetchApplication(offerId, applicationId);
    }

    @RequestMapping(value = "/updateApplication/{offerId}/{applicationId}", consumes = "application/json", produces = "application/json", method = RequestMethod.PUT)
    public UpdateApplicationResponse updateApplication(@RequestBody @Valid UpdateApplicationRequest updateApplicationRequest, @PathVariable("offerId") Long offerId, @PathVariable("applicationId") Long applicationId) {
        return applicationRecruitmentService.updateApplication(updateApplicationRequest, offerId, applicationId);
    }
}

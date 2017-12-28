package service.application;

import request.application.SaveApplicationRequest;
import request.application.UpdateApplicationRequest;
import response.application.FetchApplicationResponse;
import response.application.FetchApplicationsResponse;
import response.application.SaveApplicationResponse;
import response.application.UpdateApplicationResponse;

/**
 * Created by chetan on 27.12.2017.
 */
public interface ApplicationRecruitmentService {

    /**
     * Saves the application
     * @param saveApplicationRequest request dto for saving the application.
     * @param offerId offerId.
     * @return response containing the application event.
     */
    SaveApplicationResponse saveApplication(SaveApplicationRequest saveApplicationRequest, Long offerId);

    /**
     * Fetches all the applications per offer.
     * @param offerId offerId.
     * @return dto containing list of all applications for the given offer id.
     */
    FetchApplicationsResponse getAllApplications(Long offerId);

    /**
     * Fetch the single application per offer.
     * @param offerId offer Id.
     * @param applicationId application Id.
     * @return dto containing the application for the given offer id.
     */
    FetchApplicationResponse fetchApplication(Long offerId, Long applicationId);

    /**
     * Update the application status for the given offer Id and application Id.
     * @param updateApplicationRequest update request dto.
     * @param offerId offer id.
     * @param applicationId application id.
     * @return dto containing application event.
     */
    UpdateApplicationResponse updateApplication(UpdateApplicationRequest updateApplicationRequest, Long offerId, Long applicationId);
}

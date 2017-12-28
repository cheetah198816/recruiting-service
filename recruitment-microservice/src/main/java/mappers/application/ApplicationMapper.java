package mappers.application;

import application.ApplicationDto;
import model.ApplicationEntity;

/**
 * Created by chetan on 27.12.2017.
 */
public class ApplicationMapper {


    public static ApplicationDto entity2dto(ApplicationEntity applicationEntity) {
        final ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setApplicationId(applicationEntity.getId());
        applicationDto.setCurrentApplicationStatus(applicationEntity.getCurrentApplicationStatus());
        applicationDto.setEmailId(applicationEntity.getEmailId());
        applicationDto.setResume(applicationEntity.getResume());
        return applicationDto;
    }
}

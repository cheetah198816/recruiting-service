import api.Resources;
import config.Configurations;
import eventhandlers.EventHandlers;
import javafx.application.Application;
import model.OfferEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import repositories.Repositories;
import service.Services;

/**
 * Created by chetan on 27.12.2017.
 */
@SpringBootApplication
@SpringBootConfiguration
@ComponentScan(basePackageClasses = {Resources.class, EventHandlers.class, Services.class, Configurations.class})
@EnableJpaRepositories(basePackageClasses = Repositories.class)
@EntityScan(basePackageClasses = OfferEntity.class)
@EnableScheduling
public class RecruitmentApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class);
    }
}

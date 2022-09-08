package eu.bigbank.tech.offsite.configuration;

import eu.bigbank.tech.offsite.controller.ApplicationController;
import eu.bigbank.tech.offsite.repository.ApplicationRepository;
import eu.bigbank.tech.offsite.service.ApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    ApplicationController applicationController(ApplicationService service) {
        return new ApplicationController(service);
    }
    @Bean
    ApplicationService applicationService(ApplicationRepository repository) {
        return new ApplicationService(repository);
    }
    @Bean
    ApplicationRepository applicationService() {
        return new ApplicationRepository();
    }
}

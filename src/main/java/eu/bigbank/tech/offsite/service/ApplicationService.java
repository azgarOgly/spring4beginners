package eu.bigbank.tech.offsite.service;

import eu.bigbank.tech.offsite.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final ApplicationRepository repository;

    public ApplicationService(@Autowired ApplicationRepository repository) {
        this.repository = repository;
    }
}

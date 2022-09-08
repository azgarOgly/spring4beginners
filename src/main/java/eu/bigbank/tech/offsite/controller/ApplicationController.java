package eu.bigbank.tech.offsite.controller;

import eu.bigbank.tech.offsite.service.ApplicationService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }
}

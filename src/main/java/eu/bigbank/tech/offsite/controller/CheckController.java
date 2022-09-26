package eu.bigbank.tech.offsite.controller;

import eu.bigbank.tech.offsite.model.Host;
import eu.bigbank.tech.offsite.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Collection;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CheckController {

    private final DiscoveryService service;

    @GetMapping("/check")
    public String check(Model model) {
        log.info("Call to DiscoveryController.check()");
        Collection<Host> hosts = service.check();

        model.addAttribute("hosts", hosts);
        return "check";
    }

}

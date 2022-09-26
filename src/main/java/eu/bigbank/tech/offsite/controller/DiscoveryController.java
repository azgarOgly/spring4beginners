package eu.bigbank.tech.offsite.controller;

import eu.bigbank.tech.offsite.model.Host;
import eu.bigbank.tech.offsite.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Slf4j
@RestController()
@RequiredArgsConstructor
public class DiscoveryController {

    private final DiscoveryService service;

    @GetMapping(
            value = {"/list", "/list/{name}", "/iam/{name}", "/Iam/{name}" },
            produces = "application/json"
    )
    public Collection<Host> list(@PathVariable(required = false) String name,
                                 HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        log.info("Call to DiscoveryController.list() from {} with name {}", remoteAddr, name);

        service.add(remoteAddr, name);
        return service.list();
    }

    @GetMapping(value = { "/list/{name}/{port}" })
    public Collection<Host> list(@PathVariable String name,
                                 @PathVariable int port,
                                 HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        log.info("Call to DiscoveryController.list() from {} with name {}", remoteAddr, name);

        service.add(remoteAddr, name, port);
        return service.list();
    }

    @GetMapping({"/contact/{address}","/contact/{address}/{name}"})
    public String contact(@PathVariable String address, @PathVariable(required = false) String name) {
        log.info("Call to DiscoveryController.contact() address {} name {}", address, name);
        return service.contact(address, name);
    }

}

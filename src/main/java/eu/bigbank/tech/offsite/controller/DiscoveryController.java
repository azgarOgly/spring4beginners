package eu.bigbank.tech.offsite.controller;

import eu.bigbank.tech.offsite.model.Host;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestController()
public class DiscoveryController {
    private final Set<Host> knownHosts = new HashSet<>();

    @GetMapping(
            value = {"/list", "/list/{name}", "/iam/{name}", "/Iam/{name}" },
            produces = "application/json"
    )
    public Set<Host> list(@PathVariable(required = false) String name,
                           HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        Host remoteHost = new Host().setIp(remoteAddr).setName(name);
        knownHosts.remove(remoteHost);
        knownHosts.add(remoteHost);

        log.info("Call to DiscoveryController.list() from {} with name {}", remoteAddr, name);
        return knownHosts;
    }
}

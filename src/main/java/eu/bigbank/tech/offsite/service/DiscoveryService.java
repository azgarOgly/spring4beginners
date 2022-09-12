package eu.bigbank.tech.offsite.service;

import eu.bigbank.tech.offsite.model.Host;
import eu.bigbank.tech.offsite.repository.DiscoveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@Service
public class DiscoveryService {

    private final DiscoveryRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String myName;

    public DiscoveryService(
            DiscoveryRepository repository,
            @Value("${my.name}") String myName) {
        this.repository = repository;
        this.myName = myName;
    }

    public void add(String remoteAddr, String name) {
        Host host = new Host()
                .setIp(remoteAddr)
                .setName(name);
        repository.add(host);
    }

    public Collection<Host> list() {
        return repository.getKnownHosts();
    }

    public String contact(String address, String name) {
        ArrayList hosts = restTemplate.getForObject(getUrl(address, name), ArrayList.class);
        // TODO string formatting with HostHelper
        return hosts != null ? hosts.toString() : "";
    }

    private String getUrl(String address, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(address).append("/list");

        if (name != null && name.trim().length() > 0) {
            sb.append("/").append(name);
        } else if (myName != null && myName.trim().length() > 0) {
            sb.append("/").append(myName);
        }

        return sb.toString();
    }
}

package eu.bigbank.tech.offsite.service;

import eu.bigbank.tech.offsite.model.Host;
import eu.bigbank.tech.offsite.model.HostWithHello;
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

    public void add(String remoteAddr, String name, int port) {
        Host host = new HostWithHello()
                .setPort(port)
                .setIp(remoteAddr)
                .setName(name);
        repository.add(host);
    }

    public Collection<Host> list() {
        return repository.getKnownHosts();
    }

    public String contact(String address, String name) {
        ArrayList hosts = restTemplate.getForObject(getListUrl(address, name), ArrayList.class);
        // TODO string formatting with HostHelper
        return hosts != null ? hosts.toString() : "";
    }

    public Collection<Host> check() {
        Collection<Host> knownHosts = repository.getKnownHosts();
        for (Host host : knownHosts) {
            if (host instanceof HostWithHello) {
                HostWithHello hostWithHello = (HostWithHello) host;
                repository.add(checkHello(hostWithHello));
            } else {
                repository.add(checkHello(host));
            }
        }
        knownHosts = repository.getKnownHosts();
        return knownHosts;
    }

    private HostWithHello checkHello(Host host) {
        HostWithHello hostWithHello =
                (HostWithHello) new HostWithHello()
                .setPort(80)
                .setIp(host.getIp())
                .setName(host.getName());
        log.info("***** " + host);
        log.info("***** " + hostWithHello);
        return checkHello(hostWithHello);
    }
    private HostWithHello checkHello(HostWithHello host) {
        HostWithHello result = (HostWithHello) new HostWithHello()
                .setPort(host.getPort())
                .setIp(host.getIp())
                .setName(host.getName());
        try {
            result.setHello(restTemplate.getForObject(getUrl(host.getIp(), host.getPort(), "/"), String.class));
        } catch (Exception e) {
            log.error("Failed to check hello on " + host, e);
            result.setError(e.getMessage());
        }
        return result;
    }

    private String getUrl(String address, int port, String path) {
        return getUrl(address + ":" + port, path);
    }
    private String getUrl(String address, String path) {
        StringBuilder sb = new StringBuilder();
        if (path != null) {
            if (path.startsWith("/")) {
                sb.append("http://").append(address).append(path);
            } else {
                sb.append("http://").append(address).append("/").append(path);
            }
        }
        return sb.toString();
    }

    private String getListUrl(String address, String name) {
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

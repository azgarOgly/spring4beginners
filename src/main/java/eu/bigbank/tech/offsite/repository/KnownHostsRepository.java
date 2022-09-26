package eu.bigbank.tech.offsite.repository;

import eu.bigbank.tech.offsite.model.Host;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class KnownHostsRepository {

    private final KnownHostsMongoRepository mongoRepo;

    public Collection<Host> getKnownHosts() {
        return mongoRepo.findAll();
    }

    public void add(Host host) {
        Collection<Host> knownHosts = getKnownHosts();
        Map<String, Host> hosts = new HashMap<>();
        knownHosts.forEach(h -> hosts.put(h.getIp(), h));
        hosts.put(host.getIp(), host);
        mongoRepo.saveAll(hosts.values());
    }
}

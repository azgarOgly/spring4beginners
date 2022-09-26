package eu.bigbank.tech.offsite.repository;

import eu.bigbank.tech.offsite.model.Host;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class DiscoveryRepository {
    private final Map<String, Host> knownHosts = new HashMap<>();

    public Collection<Host> getKnownHosts() {
        List<Host> result = new ArrayList<>(knownHosts.size());
        result.addAll(knownHosts.values());
        return result;
    }

    public void add(Host host) {
        knownHosts.remove(host.getIp());
        knownHosts.put(host.getIp(), host);
    }
}

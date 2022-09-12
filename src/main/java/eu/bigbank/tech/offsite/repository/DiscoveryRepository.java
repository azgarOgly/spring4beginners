package eu.bigbank.tech.offsite.repository;

import eu.bigbank.tech.offsite.model.Host;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DiscoveryRepository {
    private final Set<Host> knownHosts = new HashSet<>();

    public Collection<Host> getKnownHosts() {
        List<Host> result = new ArrayList<>(knownHosts.size());
        result.addAll(knownHosts);
        return result;
    }

    public void add(Host host) {
        knownHosts.remove(host);
        knownHosts.add(host);
    }
}

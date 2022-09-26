package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Host {
    private String ip;
    private String name;

    public Host(Host host) {
        this.ip = host.getIp();
        this.name = host.getName();
    }
}

package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Host {
    @Id
    private String ip;
    private String name;
    private int port;

    public Host(Host host) {
        this.ip = host.getIp();
        this.name = host.getName();
        this.port = host.getPort();
    }
}

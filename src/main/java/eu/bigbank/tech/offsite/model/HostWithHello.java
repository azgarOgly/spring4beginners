package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class HostWithHello extends Host {

    private int port;
    private String hello;
    private String error;

    public HostWithHello(Host host) {
        super(host);
    }

}

package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class HostWithHello extends Host {
    private int port;
    private String hello;
    private String error;
}

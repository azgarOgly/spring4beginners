package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Host {
    private String ip;
    private String name;
}

package eu.bigbank.tech.offsite.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Host {
    private String ip;
    @EqualsAndHashCode.Exclude
    private String name;
}

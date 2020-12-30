package dropwizard.config;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.discovery.DiscoveryFactory;
import io.dropwizard.riemann.RiemannConfig;
import io.dropwizard.util.Duration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProjectConfig extends Configuration {

    @NotNull
    private final int defaultSize;

    @Valid
    @NotNull
    private DiscoveryFactory discovery = new DiscoveryFactory();

    @JsonProperty("riemann")
    public RiemannConfig getRiemann() {
        return riemann;
    }

    @JsonProperty("riemann")
    public void setRiemann(RiemannConfig riemann) {
        this.riemann = riemann;
    }

    @NotNull
    @Valid
    private RiemannConfig riemann;

    @JsonProperty("discovery")
    public DiscoveryFactory getDiscoveryFactory() {
        discovery = new DiscoveryFactory();
        return discovery;
    }

    @JsonProperty("discovery")
    public void setDiscoveryFactory(DiscoveryFactory discoveryFactory) {
        this.discovery = discoveryFactory;
    }


    @JsonCreator
    public ProjectConfig(@JsonProperty("defaultSize") int defaultSize) {
        this.defaultSize = defaultSize;
    }

    public int getDefaultSize() {
        return defaultSize;
    }
}

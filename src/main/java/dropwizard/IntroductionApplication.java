package dropwizard;

import dropwizard.config.ProjectConfig;
import dropwizard.controller.BrandController;
import dropwizard.entity.BrandEntity;
import dropwizard.repository.BrandRepository;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.discovery.DiscoveryBundle;
import io.dropwizard.discovery.DiscoveryFactory;
import io.dropwizard.riemann.RiemannBundle;
import io.dropwizard.riemann.RiemannConfig;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Arrays;
import java.util.List;

public class IntroductionApplication extends Application<ProjectConfig> {


    private final DiscoveryBundle<ProjectConfig> discoveryBundle = new DiscoveryBundle<ProjectConfig>() {
        @Override
        public DiscoveryFactory getDiscoveryFactory(ProjectConfig configuration) {
            return configuration.getDiscoveryFactory();
        }

    };

    public static void main(String[] args) throws Exception {
        new IntroductionApplication().run("server","application.yml");
    }

    @Override
    public void run(ProjectConfig configuration, Environment environment) throws Exception {
        int defaultSize = configuration.getDefaultSize();


        BrandRepository brandRepository = new BrandRepository(initBrands());
        BrandController brandResource = new BrandController(defaultSize, brandRepository);

        environment
                .jersey()
                .register(brandResource);
    }

    private List<BrandEntity> initBrands() {
        BrandEntity entity =new BrandEntity();
        entity.setId(1L);
        entity.setName("Name");
        return Arrays.asList(entity);
    }

    @Override
    public void initialize(Bootstrap<ProjectConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        bootstrap.addCommand(new WaitingCmd(this));
        bootstrap.addBundle(discoveryBundle);
        bootstrap.addBundle(new RiemannBundle<ProjectConfig>() {
            @Override
            public RiemannConfig getRiemannConfiguration(ProjectConfig configuration) {
                return configuration.getRiemann();
            }
        });
        super.initialize(bootstrap);
    }
}

package pm.sboot.sdrest.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("PROD")
public class ProductionConfiguration {
    // ...
}

package com.doubleespresso.whitenoise;

import com.doubleespresso.whitenoise.health.TemplateHealthCheck;
import com.doubleespresso.whitenoise.resources.WhiteNoiseResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhiteNoiseApplication extends Application<WhiteNoiseConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteNoiseApplication.class);

    public static void main(String[] args) throws Exception {
        new WhiteNoiseApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<WhiteNoiseConfiguration> bootstrap) {
        LOGGER.debug("Application initialised!");
        // nothing to do yet
    }

    @Override
    public void run(WhiteNoiseConfiguration configuration, Environment environment) {
        final WhiteNoiseResource resource = new WhiteNoiseResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

        LOGGER.info("Application running!");
    }
}
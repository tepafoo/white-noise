package com.doubleespresso.whitenoise;

import com.doubleespresso.whitenoise.health.TemplateHealthCheck;
import com.doubleespresso.whitenoise.resources.WhiteNoiseResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WhiteNoiseApplication extends Application<WhiteNoiseConfiguration> {
    public static void main(String[] args) throws Exception {
        new WhiteNoiseApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<WhiteNoiseConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(WhiteNoiseConfiguration configuration, Environment environment) {
        final WhiteNoiseResource resource = new WhiteNoiseResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }
}
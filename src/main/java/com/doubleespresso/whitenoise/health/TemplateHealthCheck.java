package com.doubleespresso.whitenoise.health;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateHealthCheck extends HealthCheck {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateHealthCheck.class);

    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {

        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            LOGGER.error("Template doesn't include a name!");
            return Result.unhealthy("template doesn't include a name");
        }

        LOGGER.warn("Template is good! But I'll log a warning anyway.");

        return Result.healthy();
    }
}
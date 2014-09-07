package com.doubleespresso.whitenoise.resources;

import com.doubleespresso.whitenoise.core.Saying;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class WhiteNoiseResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WhiteNoiseResource.class);



    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public WhiteNoiseResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {

        LOGGER.error("GET /hello-world called. Surely this is an error!");

        final String value = String.format(template, name.or(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
package se.rejjd.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.rejjd.resource.UserResource;

@Component
public class JerseyConfig extends ResourceConfig{
	
	public JerseyConfig() {
		register(UserResource.class);
	}

}

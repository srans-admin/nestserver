package com.srans.nestserver.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
public class JacksonConfig {

	
	  
	  @Bean public Module hibernate5Module() { Hibernate5Module hibernate5Module =
	  new Hibernate5Module(); hibernate5Module.enable(
	  Hibernate5Module.Feature.FORCE_LAZY_LOADING ); hibernate5Module.disable(
	  Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION ); return hibernate5Module;
	  }
	 

}

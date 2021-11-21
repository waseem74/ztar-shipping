package com.ztar.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ShipmentConfiguration {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.messages.basename}")
	private String propertiesBasename;

	@Bean
	public ResourceBundleMessageSource messageSource() {
		logger.info("Get the resource bundle");

		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename(propertiesBasename);
		return rs;
	}

}

package com.ztar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Waseem Zawaideh
 */
@SpringBootApplication
public class ZtarShippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtarShippingApplication.class, args);

	}
}

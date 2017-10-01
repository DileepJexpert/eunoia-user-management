package com.eunoia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableJpaAuditing
public class EunoiaUserManagementApplication {
	
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		    if (!registry.hasMappingForPattern("/mails/**")) {
		        registry.addResourceHandler("/mails/**").addResourceLocations("classpath:/resources/mails/");
		    }
		    if (!registry.hasMappingForPattern("/templates/**")) {
		        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/resources/templates/");
		    }
		}

	public static void main(String[] args) {
		SpringApplication.run(EunoiaUserManagementApplication.class, args);
	}
}

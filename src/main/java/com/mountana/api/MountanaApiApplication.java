package com.mountana.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MountanaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MountanaApiApplication.class, args);
	}

}

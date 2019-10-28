package com.fedsea.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fedsea.app.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })

public class FedseaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FedseaApplication.class, args);
	}
}

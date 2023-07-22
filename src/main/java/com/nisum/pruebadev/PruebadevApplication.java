package com.nisum.pruebadev;

import com.nisum.pruebadev.config.ValidationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ValidationConfig.class)
public class PruebadevApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebadevApplication.class, args);
	}

}

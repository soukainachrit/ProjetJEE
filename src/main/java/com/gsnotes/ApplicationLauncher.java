package com.gsnotes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationLauncher {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationLauncher.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationLauncher.class, args);
	}


}

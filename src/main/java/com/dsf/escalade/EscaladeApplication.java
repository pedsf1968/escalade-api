package com.dsf.escalade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@RestController
public class EscaladeApplication extends SpringBootServletInitializer {

	@Autowired
	private Environment env;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		return application.sources(EscaladeApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(EscaladeApplication.class, args);
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}

package br.org.sistemafesu;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SistemaFesuApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SistemaFesuApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(@NotNull SpringApplicationBuilder application) {
		return application.sources(SistemaFesuApplication.class);
	}
}

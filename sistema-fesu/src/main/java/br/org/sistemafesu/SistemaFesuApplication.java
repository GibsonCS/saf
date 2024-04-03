package br.org.sistemafesu;

import br.org.sistemafesu.entity.Aluno;
import br.org.sistemafesu.entity.Role;
import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

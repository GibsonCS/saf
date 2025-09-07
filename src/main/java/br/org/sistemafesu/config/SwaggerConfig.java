package br.org.sistemafesu.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    static {
        SpringDocUtils.getConfig().replaceWithClass(org.springframework.data.domain.Pageable.class, org.springdoc.core.converters.models.Pageable.class);
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi actuator() {
        return GroupedOpenApi.builder()
                .group("actuator")
                .pathsToMatch("/actuator/**")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Título da API")
                        .version("Versão 1.0")
                        .description("Descrição da API")
                        .termsOfService("Termos de Serviço")
                        .license(new License().name("Licença da API").url("https://github.com/celebrimbor02/TrabalhoJAVA")));
    }
}

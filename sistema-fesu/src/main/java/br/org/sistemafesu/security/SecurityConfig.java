package br.org.sistemafesu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityDatabaseService securityDatabaseService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityDatabaseService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig
                                    .requestMatchers(new AntPathRequestMatcher("/logout")).permitAll()
                                    .requestMatchers(new AntPathRequestMatcher("/assets/**")).permitAll()
                                    .anyRequest().authenticated();


                        })
                .formLogin(form -> {
                    form
                            .loginPage("/login")
                            .permitAll();
                })
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                .csrf(csrf -> csrf.disable());
        return http.build();


    }
    /*@Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("Gibson")
                .password("40028922")
                .roles("USERS")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/

    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}

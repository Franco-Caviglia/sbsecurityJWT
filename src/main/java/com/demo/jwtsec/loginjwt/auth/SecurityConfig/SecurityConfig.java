package com.demo.jwtsec.loginjwt.auth.SecurityConfig;


import com.demo.jwtsec.loginjwt.auth.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

//import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //Cross-site request forgery; Autenticacion basada en un token csrf;
                .authorizeHttpRequests( authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll()//Indica que todos los request con ruta /auth/ es publico;
                                .requestMatchers("/api/admin/**").hasAuthority("ADMINISTRATOR")//Rutas para el rol de admin;
                                .requestMatchers("/api/user/**").hasAnyAuthority("USER", "ADMINISTRATOR")//Rutas para el rol de user;
                                .requestMatchers("/test/**").permitAll()//Zona de pruebas para el postman;
                                .anyRequest().authenticated())
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

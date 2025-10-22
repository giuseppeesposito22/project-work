package com.project.java.project_work.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        
        http.authorizeHttpRequests(requests -> requests
        .requestMatchers("/videogames/create", "/videogames/edit/**").hasAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/videogames/**").hasAnyAuthority("ADMIN")
        .requestMatchers("/videogames", "/videogames/**").hasAnyAuthority("ADMIN", "USER")
        .requestMatchers("/platforms", "/platforms/**").hasAnyAuthority("ADMIN", "USER")
        .requestMatchers("/").permitAll()
        .requestMatchers("/**").permitAll())
        .formLogin(Customizer.withDefaults())
        .authenticationProvider(authenticationProvider())
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // USA LA CONFIGURAZIONE CUSTOM
        .csrf(csrf -> csrf.disable());
        
        
        
        return http.build();
    }




    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;

    }


    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*")); // Permette tutti i domini
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
}

    

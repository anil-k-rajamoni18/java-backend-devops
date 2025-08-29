package com.learn.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,  "/api/**", "/h2-console/**").permitAll()   // allow H2 console
                        .anyRequest().authenticated()                   // secure everything else
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**", "/h2-console/**")) // disable CSRF for H2
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // allow frames (H2 console is inside an iframe)
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // use basic form login for other endpoints
                .httpBasic(Customizer.withDefaults()); // Basic Auth

        return http.build();
    }
}


package br.com.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityRequests {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomFilter customFilter,
                                                   NeedsAuthenticationEntryPoint entryPointConfig) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(basic -> basic.authenticationEntryPoint(entryPointConfig))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(customFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        // configure what requisition need of authenticated
                        .requestMatchers(HttpMethod.GET, "token/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/token/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
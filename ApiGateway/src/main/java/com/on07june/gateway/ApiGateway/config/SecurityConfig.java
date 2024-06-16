package com.on07june.gateway.ApiGateway.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {

		/* MODERN WAY */
		httpSecurity
			.authorizeExchange(exchange -> 
					exchange.anyExchange()
							.authenticated())
			.oauth2Client(withDefaults())
			.oauth2ResourceServer(configurer -> 
								configurer.jwt(jwtConfigurer -> {}));
		
		

		/* DEPRECATED WAY */
//		httpSecurity
//			.authorizeExchange()
//			.anyExchange()
//			.authenticated()
//			.and()
//			.oauth2Client()
//			.and()
//			.oauth2ResourceServer()
//			.jwt();

		return httpSecurity.build();

	}

}
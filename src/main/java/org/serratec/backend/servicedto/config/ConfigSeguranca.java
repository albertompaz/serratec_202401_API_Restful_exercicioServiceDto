package org.serratec.backend.servicedto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {
	
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(requests -> {
			requests.requestMatchers(HttpMethod.GET, "/funcionarios").permitAll();
			requests.requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")
			.anyRequest().authenticated();
		}).sessionManagement(session -> {
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		});
		return http.build();
	}
	
	@Bean InMemoryUserDetailsManager userdetailService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("teste")
				.password("123456")
				.roles("RH").build();
		return new InMemoryUserDetailsManager(user);
	}

}

package com.example.realestate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Teddy Smith
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AppUserDetailsService userDetailsService;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/oglasi/postavka", "/oglasi/izmena/*", "/oglasi/brisanje/*").authenticated()
				.anyRequest().permitAll())
			.formLogin(form -> form.loginPage("/korisnici/prijava")
				.loginProcessingUrl("/korisnici/prijava")
				.usernameParameter("email")
				.defaultSuccessUrl("/oglasi")
				.failureUrl("/korisnici/prijava?error=true").permitAll())
			.logout(logout -> logout.logoutRequestMatcher(
				new AntPathRequestMatcher("/korisnici/odjava")).permitAll());
		return http.build();
	}

	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}

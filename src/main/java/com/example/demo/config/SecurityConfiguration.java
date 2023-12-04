package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomUserDetailsService;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	 @Autowired
	    private CustomUserDetailsService userDetailsService;

	    // ...

	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(encoder());
	        return authProvider;
	    }

	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	    	.authorizeHttpRequests((requests) -> requests
	    	    .requestMatchers("/registration**", "/js/**", "/css/**", "/img/**").permitAll()
	    	    .requestMatchers(new AntPathRequestMatcher("/order/**")).hasAnyRole("USER", "ADMIN")
	    	    .anyRequest().hasRole("ADMIN")
	    	)
	        .formLogin((form) -> form
	            .loginPage("/login")
	            .permitAll()
	            .defaultSuccessUrl("/order", true)
	        )
	        .logout((logout) -> logout
	        		.invalidateHttpSession(true)
	        		.clearAuthentication(true)
	        		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        		.logoutSuccessUrl("/login?logout")
	        		.permitAll());

	    return http.build();
	    }

	   
	       @Bean
	       public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	           return http.getSharedObject(AuthenticationManagerBuilder.class)
	               .authenticationProvider(authProvider())
	               .build();
	       }
}
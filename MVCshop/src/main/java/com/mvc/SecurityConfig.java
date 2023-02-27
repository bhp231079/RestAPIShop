package com.mvc;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mvc.filter.AuthoritiesLoggingAfterFilter;
import com.mvc.filter.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;// can fai co 1 bean

	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		/// 123456 -> asdaaszxcsdas: bao mat hon
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		return bCryptPasswordEncoder;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		
//				.ADDFILTERBEFORE(NEW JWTTOKENVALIDATORFILTER(), BASICAUTHENTICATIONFILTER.CLASS)
//				.ADDFILTERAFTER(NEW JWTTOKENGENERATORFILTER(), USERNAMEPASSWORDAUTHENTICATIONFILTER.CLASS)
				.authorizeRequests()
				.antMatchers("/api/category").hasAnyRole("ADMIN")
				.antMatchers("/api/user").authenticated()
				.anyRequest().permitAll().and().csrf().disable().cors()
				.configurationSource(new CorsConfigurationSource() {

					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("*"));
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setAllowedMethods(Collections.singletonList("*"));
//						config.setExposedHeaders(Arrays.asList("Authorization"));
//						config.setMaxAge(3600L);
						return config;
					}
				}).and().httpBasic().disable().formLogin().disable();
		http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


}

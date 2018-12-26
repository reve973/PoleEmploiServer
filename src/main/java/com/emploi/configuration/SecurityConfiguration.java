// https://blog.angular-university.io/angular-jwt-authentication/

// init
// curl -i -H "Content-Type: application/json" -X POST -d '{"username": "admin","password": "123"}' http://localhost:8080/api/auth/init

// sans token
// curl http://localhost:8080/api/vehicule/get/all

// avec token
// curl -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImNsaWVudDEiLCJyb2xlcyI6IlJPTEVfQ0xJRU5UIiwidXNlclNlY3JldCI6IlNFQ1JFVCIsImV4cCI6MTUzOTkzMDQ5MSwiaWF0IjoxNTM5OTMwMTkxfQ.urpMPr50llvsp02ZCOod8mgGNB1bGbfaBWBVbOV5qlTEFXgu2fsPWonkGdH69PddxDO5E6hpHv0xk8oxP3qEmw" http://localhost:8080/api/vehicule/get/all

package com.emploi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.emploi.security.JwtAuthenticationTokenFilter;
import com.emploi.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	private UserDetailsServiceImpl securityService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(securityService).passwordEncoder(passwordEncoder());
	}	
	
	protected void configure(HttpSecurity http) throws Exception {
		if (true) {
			http.csrf().disable();
			http
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	            .authorizeRequests()
	            .antMatchers("/api/auth/login").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .cors();
			http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		} else {
			http.csrf().disable();
			http.sessionManagement().and().authorizeRequests().antMatchers("/login", "/api/**", "/").permitAll().anyRequest()
					.authenticated().and().formLogin().loginPage("/login")./*defaultSuccessUrl("/login?success")
					.failureUrl("/login?failure").*/permitAll().and().logout().invalidateHttpSession(true)
					/*.logoutUrl("/login?logout")*/.permitAll();
		}
	}
	
	/*@Bean
	public PasswordEncoder passwordEncoder() {
	    PasswordEncoder encoder = new BCryptPasswordEncoder();
	    return encoder;
	}	
	*/
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
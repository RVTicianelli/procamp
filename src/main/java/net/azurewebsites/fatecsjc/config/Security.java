package net.azurewebsites.fatecsjc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import net.azurewebsites.fatecsjc.security.JWTAuthenticationFilter;
import net.azurewebsites.fatecsjc.security.JWTAuthorizationFilter;
import net.azurewebsites.fatecsjc.security.JWTUtil;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
		
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] POST_MATCHERS_USER = {
			"/usuario/**",
			"/localidade/**"
	};
	
	private static final String[] GET_PUBLIC_MATCHERS = {
			"/campanha/**",
			"/tipocampanha/**"
	};
	
	private static final String[] GET_UPDATE_DELETE_MATCHERS = {
			"/usuario/**",
			"/cidade/**",
			"/campanha/**",
			"/preferencia/**",
			"/ceps/**",
			"/contato/**",
			"/estado/**",
			"/localidade/**",
			"/responsavel/**",
			"/tipocampanha/**",
			"/tipocontato/**",
			"/tipolocalidade/**",
			"/tiporesponsavel/**",
			"/map/**"
	};

	private static final String[] POST_MATCHERS = {
			"/cidade/**",
			"/campanha/**",
			"/preferencia/**",
			"/ceps/**",
			"/contato/**",
			"/estado/**",
			"/responsavel/**",
			"/tipocampanha/**",
			"/tipocontato/**",
			"/tipolocalidade/**",
			"/tiporesponsavel/**",
			"/map/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests().antMatchers(HttpMethod.GET, GET_UPDATE_DELETE_MATCHERS).authenticated()
								.antMatchers(HttpMethod.PUT, GET_UPDATE_DELETE_MATCHERS).authenticated()
								.antMatchers(HttpMethod.DELETE, GET_UPDATE_DELETE_MATCHERS).authenticated()
								.antMatchers(HttpMethod.POST, POST_MATCHERS).authenticated()
								.antMatchers(HttpMethod.POST, POST_MATCHERS_USER).permitAll()
								.antMatchers(HttpMethod.GET, GET_PUBLIC_MATCHERS).permitAll()
								.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	

	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();

		corsConfiguration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addExposedHeader("Authorization");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", corsConfiguration);

		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}


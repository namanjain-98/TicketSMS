package com.namanjain.configurations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.NimbusAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.namanjain.entity.CustomOauth2User;
import com.namanjain.service.CustomOauth2UserService;
import com.namanjain.service.PersonService;
import com.namanjain.service.UserService;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:database.properties")
@ComponentScan({ "com.namanjain" })
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";

	@Autowired
	private Environment env;

	@Autowired
	PersonService personService;

	@Autowired
	UserService userService;

	@Autowired
	CustomOauth2UserService customOauth2UserService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(personService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login", "/", "/user/add", "/oauth/*").permitAll().antMatchers("/*")
				.hasAnyRole("ADMIN").antMatchers("/ticket/*").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
				.antMatchers("/employee/*").hasAnyRole("ADMIN").and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/").failureUrl("/login?error=true").permitAll().and().oauth2Login()
				.loginPage("/login").authorizationEndpoint()
				.authorizationRequestRepository(authorizationRequestRepository()).and()
				.clientRegistrationRepository(clientRegistrationRepository())
				.authorizedClientService(authorizedClientService()).tokenEndpoint()
				.accessTokenResponseClient(accessTokenResponseClient()).and().userInfoEndpoint()
				.oidcUserService(customOauth2UserService).and().failureHandler(new AuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {

						System.out.println(exception);
						System.out.println("Failed");

					}
				}).successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						
						  CustomOauth2User oauthUser = (CustomOauth2User)authentication.getPrincipal();
						  
						  userService.processOAuthPostLogin(oauthUser);
						 
						  System.out.println("Succeess");
						
						
						  response.sendRedirect("/ticketsms4/");
						 
					

					}
				})
				.and()
				.logout()
				.logoutSuccessUrl("/login?logout=true")
				.invalidateHttpSession(true)
				.permitAll()
				.and()
				.csrf()
				.disable();

	}

	@SuppressWarnings("deprecation")
	private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {

		return new NimbusAuthorizationCodeTokenResponseClient();
	}

	private AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
		return new HttpSessionOAuth2AuthorizationRequestRepository();
	}

	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
	}

	private ClientRegistration googleClientRegistration() {
		String client = "google";

		String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-id");

		String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client + ".client-secret");

		String redirectUri = env.getProperty(CLIENT_PROPERTY_KEY + client + ".redirect-uri");
		
	

		System.out.print(redirectUri);

		return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId(clientId).clientSecret(clientSecret)
				.redirectUri(redirectUri).build();
	}

	public OAuth2AuthorizedClientService authorizedClientService() {
		return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
	}

	/*
	 * public ClientRegistrationRepository clientRegistrationRepository() {
	 * 
	 * ClientRegistration registration = getRegistration("google");
	 * 
	 * return new InMemoryClientRegistrationRepository(registration); }
	 * 
	 * private ClientRegistration getRegistration(String client) {
	 * 
	 * String clientId = env.getProperty(CLIENT_PROPERTY_KEY + client +
	 * ".client-id");
	 * 
	 * String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + client +
	 * ".client-secret");
	 * 
	 * return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(clientId).
	 * clientSecret(clientSecret).build();
	 * 
	 * }
	 * 
	 * public OAuth2AuthorizedClientService authorizedClientService() {
	 * 
	 * return new
	 * InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository()); }
	 * 
	 * 
	 * @Bean public BCryptPasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */

}

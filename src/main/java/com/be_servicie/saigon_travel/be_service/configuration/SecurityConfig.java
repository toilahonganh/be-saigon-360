package com.be_servicie.saigon_travel.be_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private String client = "http://localhost:8386";

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/auth/google").permitAll() 
            .anyRequest().authenticated() 
        )
        .oauth2Login(oauth2 -> oauth2
            .authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint
                            .authorizationRequestResolver(
                                new CustomAuthorizationRequestResolver(
                                                new DefaultOAuth2AuthorizationRequestResolver(
                                                                clientRegistrationRepository,
                                                                "/oauth2/authorization"))))
            .successHandler((request, response, authentication) -> {
                    OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                    OAuth2AuthorizedClient authorizedClient = authorizedClientService
                                    .loadAuthorizedClient(authToken
                                                    .getAuthorizedClientRegistrationId(),
                                                    authToken.getName());

                                        if (authorizedClient != null) {
                                            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
                                            System.out.println("Access Token: " + accessToken.getTokenValue());
                                        
                                            if (authorizedClient.getRefreshToken() != null) {
                                                System.out.println("Refresh Token: " + authorizedClient.getRefreshToken().getTokenValue());
                                            } else {
                                                System.out.println("No Refresh Token available.");
                                            }
                                        }                                                    

                    response.sendRedirect(client + "/auth-success");
            }));
        return http.build();
    }
}

package com.be_servicie.saigon_travel.be_service.services.app_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.be_servicie.saigon_travel.be_service.dto.response.Oauth2Tokens;

@Service
public class Oauth2Service {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public Oauth2Tokens getOAuth2Tokens(OAuth2AuthenticationToken authenticationToken) {
        String clientRegistrationId = authenticationToken.getAuthorizedClientRegistrationId();
        String principalName = authenticationToken.getName();
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(clientRegistrationId,
                principalName);

        String accessToken = client.getAccessToken().getTokenValue();
        String refreshToken = client.getRefreshToken() != null ? client.getRefreshToken().getTokenValue() : null;

        return new Oauth2Tokens(accessToken, refreshToken);
    }
}

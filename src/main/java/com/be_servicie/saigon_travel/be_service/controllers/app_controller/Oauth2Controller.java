package com.be_servicie.saigon_travel.be_service.controllers.app_controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.be_servicie.saigon_travel.be_service.dto.response.Oauth2Tokens;
import com.be_servicie.saigon_travel.be_service.dto.response.UserDTO;
import com.be_servicie.saigon_travel.be_service.services.app_service.AuthService;
import com.be_servicie.saigon_travel.be_service.services.app_service.Oauth2Service;

@RestController
public class Oauth2Controller {
    @Autowired
    private AuthService authService;

    @Autowired
    private Oauth2Service oAuthService;

    @GetMapping("/auth/google")
    public ResponseEntity<?> loginWithGoogle(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body("Please login via Google OAuth2");
        }

        Map<String, Object> userAttributes = principal.getAttributes();

        try {
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder
                            .getContext()
                            .getAuthentication();
            Oauth2Tokens tokens = oAuthService.getOAuth2Tokens(authenticationToken);

            UserDTO user = authService.createUserFromGoogle(userAttributes, tokens.getRefreshToken());

            // String mess = String.format(
            //                 "{\"type\":\"login\",\"access_token\":\"%s\", \"refresh_token\":\"%s\", \"user_id\":\"%s\"}",
            //                 tokens.getAccessToken(), tokens.getRefreshToken(), user.getId());
            Map<String, String> mess = new HashMap<>();
            mess.put("type", "login");
            mess.put("access_token", tokens.getAccessToken());
            mess.put("refresh_token", tokens.getRefreshToken());
            mess.put("user_id", user.getId());


            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("tokens_info", mess);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error processing user data from Google: " + e.getMessage());
        }
    }
}

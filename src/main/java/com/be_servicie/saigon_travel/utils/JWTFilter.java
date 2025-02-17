package com.be_servicie.saigon_travel.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

public class JWTFilter extends OncePerRequestFilter {
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public JWTFilter(RestTemplate restTemplate) {
        this.webClient = WebClient.builder().build();
        this.restTemplate = restTemplate;
    }

    String errorCode = null;
    String errorMessage = null;

    @Value("${spring.security.oauth2.provider.google.token-info-uri}")
    private String googleTokenInfoUrl;

    @Value("${spring.security.oauth2.provider.google.token-uri}")
    private String tokenUri;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    String clientSecret;

    public enum TokenValidationResult {
        VALID, EXPIRED, INVALID, SERVER_ERROR, UNKNOWN_ERROR;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        String refreshToken = request.getHeader("x-refresh-token");
        String sessionId = request.getHeader("x-session-id");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            TokenValidationResult validationResult = validateGoogleToken(token, refreshToken);
            System.out.println("Token validation result: " + validationResult);

            switch (validationResult) {
                case VALID:
                    break;

                case EXPIRED:
                    errorCode = "TOKEN_EXPIRED";
                    String newAccessToken = getNewAccessToken(refreshToken);
                    if (newAccessToken == null) {
                        errorMessage = "The access token has expired.";
                        // sendMessage(sessionId, errorCode, errorMessage, newAccessToken);
                    } else {
                        errorMessage = "Token is renewed";
                        // sendMessage(sessionId, errorCode, errorMessage, newAccessToken);
                        return;
                    }
                    break;

                case INVALID:
                    errorCode = "TOKEN_INVALID";
                    errorMessage = "The access token is invalid.";
                    // sendMessage(sessionId, errorCode, errorMessage, null);
                    return;

                case SERVER_ERROR:
                    break;

                default:
                    errorCode = "UNKNOWN_ERROR";
                    errorMessage = "An unknown error occurred.";
                    break;
            }
        }

        filterChain.doFilter(request, response);
    }

    private TokenValidationResult validateGoogleToken(String accessToken, String ref) {
        try {

            Map<String, Object> tokenInfo = webClient.get()
                    .uri(googleTokenInfoUrl + "?access_token=" + accessToken)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class).flatMap(body -> {

                                System.err.println(
                                        "Google Token Validation Failed: HTTP Status - " + response.statusCode());
                                System.err.println("Google API Response Body: " + body);
                                return Mono.error(new RuntimeException("Google token validation failed: " + body));
                            }))
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();

            // Kiểm tra token hợp lệ
            if (clientId.equals(tokenInfo.get("aud"))) {
                return TokenValidationResult.VALID;
            } else {
                return TokenValidationResult.INVALID;
            }

        } catch (Exception e) {
            if (e.getMessage().contains("Invalid Value") || e.getMessage().contains("expired_token")) {
                System.out.println("access token invalid value");
                return TokenValidationResult.INVALID;

            } else {
                return TokenValidationResult.SERVER_ERROR;
            }
        }
    }
    public String getNewAccessToken(String refreshToken) {
        try {
            System.out.println("Attempting to get a new access token...");
            String url = tokenUri;

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("client_id", clientId);
            formData.add("client_secret", clientSecret);
            formData.add("refresh_token", refreshToken);
            formData.add("grant_type", "refresh_token");

            ResponseEntity<Map> response = restTemplate.postForEntity(url, formData, Map.class);
            Map<String, Object> responseBody = response.getBody();

            System.out.println("Access token refreshed successfully");

            return (String) responseBody.get("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

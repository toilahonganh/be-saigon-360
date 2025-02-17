package com.be_servicie.saigon_travel.be_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Oauth2Tokens {
    private String accessToken;
    private String refreshToken;
}

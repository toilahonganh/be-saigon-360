package com.be_servicie.saigon_travel.be_service.dto.response;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String name;
    private String imageUrl;
    private Set<String> roles;
}

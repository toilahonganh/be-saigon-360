package com.be_servicie.saigon_travel.be_service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.be_servicie.saigon_travel.be_service.entity.Role;
import com.be_servicie.saigon_travel.be_service.entity.User;
import com.be_servicie.saigon_travel.be_service.dto.request.UserCreateRequest;
import com.be_servicie.saigon_travel.be_service.dto.response.UserDTO;

@Component
public class UserToDTOMapper extends DTOMapper<User, UserDTO>{
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .roles(user.getRoles().stream()
                        .map(Role::getSlug)
                        .collect(Collectors.toSet()))
                .build();
    }

    public UserDTO toDto(UserCreateRequest request) {
        return UserDTO.builder()
                .email(request.getEmail())
                .name(request.getName())
                .roles(request.getRole().stream()
                        .map(Role::getSlug)
                        .collect(Collectors.toSet())) 
                .build();
    }
}

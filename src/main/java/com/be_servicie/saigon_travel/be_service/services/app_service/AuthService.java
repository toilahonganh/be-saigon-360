package com.be_servicie.saigon_travel.be_service.services.app_service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.be_servicie.saigon_travel.be_service.dto.response.UserDTO;
import com.be_servicie.saigon_travel.be_service.entity.Role;
import com.be_servicie.saigon_travel.be_service.entity.User;
import com.be_servicie.saigon_travel.be_service.mapper.UserToDTOMapper;
import com.be_servicie.saigon_travel.be_service.repository.AdminRepository;
import com.be_servicie.saigon_travel.be_service.services.RoleService;

@Service
public class AuthService {
    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserToDTOMapper userToDTOMapper;

    public UserDTO createUserFromGoogle(Map<String, Object> userAttributes, String refreshToken) throws Exception {
        String email = (String) userAttributes.get("email");
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            // log.info("Login successfully!");
            return userToDTOMapper.toDto(existingUser.get());
        }

        Role userRole = roleService.findById("role2")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        String id = (String) userAttributes.get("sub");
        LocalDateTime expiryRefreshToken = LocalDateTime.now().plusMonths(1);
        User user = User.builder()
                .id(id)
                .email(email)
                .name((String) userAttributes.get("name"))
                .imageUrl((String) userAttributes.get("picture"))
                .status(true)
                .roles(new HashSet<>(Set.of(userRole)))
                .token(refreshToken)
                .expiry(expiryRefreshToken)
                .build();

        // log.info("Tao moi nguoi dung: {}", user);

        User savedUser = userRepository.save(user);

        return userToDTOMapper.toDto(savedUser);
    }
}

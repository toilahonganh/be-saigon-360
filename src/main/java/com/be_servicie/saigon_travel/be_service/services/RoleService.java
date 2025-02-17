package com.be_servicie.saigon_travel.be_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.be_servicie.saigon_travel.be_service.entity.Role;
import com.be_servicie.saigon_travel.be_service.services.app_service.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }
}

package com.be_servicie.saigon_travel.be_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.be_servicie.saigon_travel.be_service.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, String>{
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}

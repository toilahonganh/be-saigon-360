package com.be_servicie.saigon_travel.be_service.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.be_servicie.saigon_travel.be_service.entity.MenuFunction;

@Repository
public interface MenuFunctionRepository extends JpaRepository<MenuFunction, String> {
    List<MenuFunction> findByActiveTrue();
}

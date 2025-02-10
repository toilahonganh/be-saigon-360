package com.be_servicie.saigon_travel.be_service.services.impl;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.be_servicie.saigon_travel.be_service.dto.request.MenuFunctionRequest;
import com.be_servicie.saigon_travel.be_service.dto.response.MenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.entity.MenuFunction;
import com.be_servicie.saigon_travel.be_service.mapper.MenuFunctionMapper;
import com.be_servicie.saigon_travel.be_service.repository.MenuFunctionRepository;
import com.be_servicie.saigon_travel.be_service.services.MenuFunctionService;


@Service
public class MenuFunctionServiceImpl implements MenuFunctionService {

    @Autowired
    private MenuFunctionRepository menuFunctionRepository;

    @Autowired
    private MenuFunctionMapper mapper;

    @Override
    public List<MenuFunctionDTO> getAllMenu() {
        return mapper.toListDTO(menuFunctionRepository.findAll());
    }

    @Override
    public Optional<MenuFunctionDTO> getMenuById(String id) {

        Optional<MenuFunction> optionalMenuFunction = menuFunctionRepository.findById(id);

        return optionalMenuFunction.map(mapper::toDTO);
    }

    @Override
    public List<MenuFunctionDTO> getMenuTrueActive() {
        return mapper.toListDTO(menuFunctionRepository.findByActiveTrue());
    }

    @Override
    @Transactional
    public MenuFunctionDTO createMenu(MenuFunctionRequest menuFunctionRequest) {
        MenuFunction menu = MenuFunction.builder()
                .name(menuFunctionRequest.getName())
                .active(menuFunctionRequest.getActive())
                .build();

        return mapper.toDTO(menuFunctionRepository.save(menu));
    }

    // @Override
    // @Transactional
    // public MenuFunctionDTO updateMenu(String id, MenuFunctionRequest menuFunctionRequest) {

    //     MenuFunction currentMenu = menuFunctionRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));

    //     currentMenu.setName(menuFunctionRequest.getName());
    //     currentMenu.setUrl(menuFunctionRequest.getUrl());
    //     currentMenu.setActive(menuFunctionRequest.getActive());

    //     return mapper.toDTO(menuFunctionRepository.save(currentMenu));
    // }

    // @Override
    // @Transactional
    // public void deleteMenuById(String id) {
    //     MenuFunction menu = menuFunctionRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("Menu not found with id: " + id));
    //     menuFunctionRepository.delete(menu);
    // }

}

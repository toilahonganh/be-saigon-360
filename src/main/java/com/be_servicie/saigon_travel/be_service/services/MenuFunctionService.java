package com.be_servicie.saigon_travel.be_service.services;

import java.util.List;
import java.util.Optional;

import com.be_servicie.saigon_travel.be_service.dto.request.MenuFunctionRequest;
import com.be_servicie.saigon_travel.be_service.dto.response.MenuFunctionDTO;

public interface MenuFunctionService {

    MenuFunctionDTO createMenu(MenuFunctionRequest menuFunctionRequest);

    List<MenuFunctionDTO> getAllMenu();

    Optional<MenuFunctionDTO> getMenuById(String id);

    List<MenuFunctionDTO> getMenuTrueActive();

    // MenuFunctionDTO updateMenu(String id, MenuFunctionRequest menuFunctionRequest);

    // void deleteMenuById(String id);
}

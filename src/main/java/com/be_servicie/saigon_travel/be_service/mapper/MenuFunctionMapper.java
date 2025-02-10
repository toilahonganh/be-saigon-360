package com.be_servicie.saigon_travel.be_service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.be_servicie.saigon_travel.be_service.dto.response.MenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.dto.response.SubMenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.entity.MenuFunction;


@Component
public class MenuFunctionMapper {
        @Autowired
        private SubMenuFunctionMapper subFunctionMapper;

        public MenuFunctionDTO toDTO(MenuFunction menuFunction) {
                List<SubMenuFunctionDTO.ShortDTO> subFunctionShortDTOs = Optional.ofNullable(menuFunction.getSubFunctions())
                                .map(subFunctions -> subFunctions.stream()
                                                .map(subFunctionMapper::toShortDTO)

                                                .collect(Collectors.toList()))
                                .orElse(Collections.emptyList());
                                                
                return MenuFunctionDTO.builder()
                                .id(menuFunction.getId())
                                .name(menuFunction.getName())
                                .active(menuFunction.getActive())
                                .subFunctions(subFunctionShortDTOs)
                                .build();
        }

        public List<MenuFunctionDTO> toListDTO(List<MenuFunction> menuFunctions) {
                return menuFunctions.stream()
                                .map(this::toDTO)
                                .collect(Collectors.toList());
        }

}

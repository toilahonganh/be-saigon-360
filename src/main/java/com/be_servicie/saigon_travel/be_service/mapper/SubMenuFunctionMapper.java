package com.be_servicie.saigon_travel.be_service.mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.be_servicie.saigon_travel.be_service.dto.response.SubMenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.entity.SubFunction;


@Component
public class SubMenuFunctionMapper {
    public SubMenuFunctionDTO.FullDTO toFullDTO(SubFunction subMenuFunction) {

        String menuFunctionId = null;
        String menuFunctionName = null;

        if (subMenuFunction.getMenuFunction() != null) {
            menuFunctionId = subMenuFunction.getMenuFunction().getId();
            menuFunctionName = subMenuFunction.getMenuFunction().getName();
        }

        return SubMenuFunctionDTO.FullDTO.builder()
                .id(subMenuFunction.getId())
                .name(subMenuFunction.getName())
                .url(subMenuFunction.getUrl())
                .active(subMenuFunction.getActive())
                .menuFunctionId(menuFunctionId)
                .menuFunctionName(menuFunctionName)
                .build();
    }

    public SubMenuFunctionDTO.ShortDTO toShortDTO(SubFunction subMenuFunction) {
        return SubMenuFunctionDTO.ShortDTO.builder()
                .name(subMenuFunction.getName())
                .url(subMenuFunction.getUrl())
                .build();
    }

    public List<SubMenuFunctionDTO.FullDTO> toListFullDTO(List<SubFunction> subMenuFunctions) {
        return subMenuFunctions.stream()
                .map(this::toFullDTO)
                .collect(Collectors.toList());
    }

    public List<SubMenuFunctionDTO.ShortDTO> toListShortDTO(List<SubFunction> subMenuFunctions) {
        return subMenuFunctions.stream()
                .map(this::toShortDTO)
                .collect(Collectors.toList());
    }
}


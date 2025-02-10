package com.be_servicie.saigon_travel.be_service.services;

import java.util.List;
import java.util.Optional;

import com.be_servicie.saigon_travel.be_service.dto.response.SubMenuFunctionDTO;


public interface SubFunctionService {

    List<SubMenuFunctionDTO.FullDTO> getAllSub();

    Optional<SubMenuFunctionDTO.FullDTO> getSub(String id);

    List<SubMenuFunctionDTO.FullDTO> getSubTrueActive();

    // SubMenuFunctionDTO.FullDTO createSub(SubFunctionRequest subFunctionRequest);

    // SubMenuFunctionDTO.FullDTO updateSub(String id, SubFunctionRequest subFunctionRequest);

    // void deleteSubById(String id);
}


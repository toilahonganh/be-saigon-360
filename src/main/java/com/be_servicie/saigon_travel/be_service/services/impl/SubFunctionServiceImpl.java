package com.be_servicie.saigon_travel.be_service.services.impl;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.be_servicie.saigon_travel.be_service.dto.response.SubMenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.mapper.SubMenuFunctionMapper;
import com.be_servicie.saigon_travel.be_service.repository.MenuFunctionRepository;
import com.be_servicie.saigon_travel.be_service.repository.SubFunctionRepository;
import com.be_servicie.saigon_travel.be_service.services.SubFunctionService;


@Service
public class SubFunctionServiceImpl implements SubFunctionService {

    @Autowired
    private SubFunctionRepository subFunctionRepository;

    @Autowired
    private MenuFunctionRepository menuFunctionRepository;

    @Autowired
    private SubMenuFunctionMapper mapper;

    @Override
    public List<SubMenuFunctionDTO.FullDTO> getAllSub() {
        return mapper.toListFullDTO(subFunctionRepository.findAll());
    }

    @Override
    public Optional<SubMenuFunctionDTO.FullDTO> getSub(String id) {
        return subFunctionRepository.findById(id)
                .map(mapper::toFullDTO);
    }

    @Override
    public List<SubMenuFunctionDTO.FullDTO> getSubTrueActive() {
        return mapper.toListFullDTO(subFunctionRepository.findByActiveTrue());
    }

    // @Override
    // @Transactional
    // public SubMenuFunctionDTO.FullDTO createSub(SubFunctionRequest subFunctionRequest) {
    //     MenuFunction menuFunction = menuFunctionRepository.findById(subFunctionRequest.getM_id())
    //             .orElseThrow(() -> new RuntimeException("MenuFunction not found"));

    //     SubFunction subFunction = SubFunction.builder()
    //             .name(subFunctionRequest.getName())
    //             .url(subFunctionRequest.getUrl())
    //             .description(subFunctionRequest.getDescription())
    //             .active(subFunctionRequest.getActive())
    //             .menuFunction(menuFunction)
    //             .build();

    //     return mapper.toFullDTO(subFunctionRepository.save(subFunction));
    // }

    // @Override
    // @Transactional
    // public SubFunctionDTO.FullDTO updateSub(String id, SubFunctionRequest subFunctionRequest) {
    //     SubFunction currentSub = subFunctionRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("SubFunction not found"));

    //     MenuFunction menuFunction = menuFunctionRepository.findById(subFunctionRequest.getM_id())
    //             .orElseThrow(() -> new RuntimeException("MenuFunction not found"));

    //     currentSub.setName(subFunctionRequest.getName());
    //     currentSub.setUrl(subFunctionRequest.getUrl());
    //     currentSub.setActive(subFunctionRequest.getActive());
    //     currentSub.setDescription(subFunctionRequest.getDescription());
    //     currentSub.setMenuFunction(menuFunction);

    //     return mapper.toFullDTO(subFunctionRepository.save(currentSub));
    // }

    // @Override
    // @Transactional
    // public void deleteSubById(String id) {
    //     SubFunction subFunction = subFunctionRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("SubFunction not found"));

    //     subFunctionRepository.delete(subFunction);
    // }

}


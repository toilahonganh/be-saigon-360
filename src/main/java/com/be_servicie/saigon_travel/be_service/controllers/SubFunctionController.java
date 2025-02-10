package com.be_servicie.saigon_travel.be_service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.be_servicie.saigon_travel.be_service.dto.response.SubMenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.services.SubFunctionService;



@RestController
@RequestMapping(path = "/api/sub-function", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubFunctionController {
    @Autowired
    private SubFunctionService subFunctionService;

    @GetMapping
    public ResponseEntity<List<SubMenuFunctionDTO.FullDTO>> getAllSub() {
        List<SubMenuFunctionDTO.FullDTO> sub = subFunctionService.getAllSub();
        return ResponseEntity.ok(sub);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubMenuFunctionDTO.FullDTO> getSubById(@PathVariable("id") String id) {

        Optional<SubMenuFunctionDTO.FullDTO> subOptional = subFunctionService.getSub(id);

        if (subOptional.isPresent()) {
            return ResponseEntity.ok(subOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<SubMenuFunctionDTO.FullDTO>> getSubTrueActive() {
        List<SubMenuFunctionDTO.FullDTO> sub = subFunctionService.getSubTrueActive();
        return ResponseEntity.ok(sub);
    }

    // @PostMapping
    // public ResponseEntity<SubFunctionDTO.FullDTO> createSub(@RequestBody SubFunctionRequest subFunctionRequest) {
    //     SubFunctionDTO.FullDTO newSub = subFunctionService.createSub(subFunctionRequest);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(newSub);
    // }

    // @PutMapping(value = "/{id}")
    // public ResponseEntity<SubFunctionDTO.FullDTO> updateSub(
    //         @PathVariable("id") String id,
    //         @RequestBody SubFunctionRequest subFunctionRequest) {

    //     SubFunctionDTO.FullDTO updatedSub = subFunctionService.updateSub(id, subFunctionRequest);
    //     return ResponseEntity.ok(updatedSub);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteSub(@PathVariable("id") String id) {
    //     subFunctionService.deleteSubById(id);
    //     return ResponseEntity.noContent().build();
    // }

}
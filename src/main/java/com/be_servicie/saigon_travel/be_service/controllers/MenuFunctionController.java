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

import com.be_servicie.saigon_travel.be_service.dto.response.MenuFunctionDTO;
import com.be_servicie.saigon_travel.be_service.services.MenuFunctionService;


@RestController
@RequestMapping(path = "/api/menu")
public class MenuFunctionController {

    @Autowired
    private MenuFunctionService menuFunctionService;

    @GetMapping
    public ResponseEntity<List<MenuFunctionDTO>> getAllMenu() {
        List<MenuFunctionDTO> menu = menuFunctionService.getAllMenu();
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuFunctionDTO> getMenuById(@PathVariable("id") String id) {

        Optional<MenuFunctionDTO> menuOptional = menuFunctionService.getMenuById(id);

        if (menuOptional.isPresent()) {
            return ResponseEntity.ok(menuOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<MenuFunctionDTO>> getMenuTrueActive() {
        List<MenuFunctionDTO> menu = menuFunctionService.getMenuTrueActive();
        return ResponseEntity.ok(menu);
    }

    // @PostMapping
    // public ResponseEntity<MenuFunctionDTO> createMenu(@RequestBody MenuFunctionRequest menuFunctionRequest) {
    //     MenuFunctionDTO newMenu = menuFunctionService.createMenu(menuFunctionRequest);

    //     return ResponseEntity.status(HttpStatus.CREATED).body(newMenu);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<MenuFunctionDTO> updateMenu(
    //         @PathVariable("id") String id,
    //         @RequestBody MenuFunctionRequest menuFunctionRequest) {

    //     MenuFunctionDTO updatedMenu = menuFunctionService.updateMenu(id, menuFunctionRequest);

    //     return ResponseEntity.ok(updatedMenu);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteMenu(@PathVariable("id") String id) {
    //     menuFunctionService.deleteMenuById(id);
    //     return ResponseEntity.noContent().build();
    // }
}
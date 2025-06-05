package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.UwagaDTO;
import org.example.projekt_mas.service.Menadzer_zmianyService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Menadzer_zmianyController {
    private final Menadzer_zmianyService menadzer_zmianyService;
    Menadzer_zmianyController(Menadzer_zmianyService service) {
        menadzer_zmianyService = service;
    }

    @PostMapping("/menadzerzy/{id}/uwagi")
    UwagaDTO dodajUwage(@PathVariable Long id, @RequestBody String uwaga) {
        return UwagaDTO.from(menadzer_zmianyService.dodajUwage(id, uwaga));
    }
}

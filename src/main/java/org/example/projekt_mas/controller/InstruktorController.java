package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.KasjerDTO;
import org.example.projekt_mas.model.Kasjer;
import org.example.projekt_mas.service.InstruktorService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class InstruktorController {
    private final InstruktorService instruktorService;

    public InstruktorController(InstruktorService instruktorService) {
        this.instruktorService = instruktorService;
    }

    @GetMapping("/instruktorzy/{id}/wynagrodzenie")
    Double obliczWynagrodzenie(@PathVariable Long id, @RequestBody LocalDate from, @RequestBody LocalDate to) {
        return instruktorService.obliczWynagrodzenie(id, from, to);
    }

    @PutMapping("/instruktorzy/{id}")
    KasjerDTO changeToKasjer(@PathVariable Long id) {
        Kasjer kasjer = instruktorService.changeToKasjer(id);
        return KasjerDTO.fromKasjer(kasjer);
    }
}

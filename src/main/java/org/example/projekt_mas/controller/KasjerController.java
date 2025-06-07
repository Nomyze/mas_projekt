package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.PracownikDTO;
import org.example.projekt_mas.model.Instruktor;
import org.example.projekt_mas.service.KasjerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class KasjerController {
    private final KasjerService kasjerService;
    public KasjerController(KasjerService kasjerService) {
        this.kasjerService = kasjerService;
    }

    @GetMapping("/kasjerzy/{id}/wynagrodzenie")
    Double obliczWynagrodzenie(@PathVariable Long id) {
        return kasjerService.obliczWynagrodzenie(id, LocalDate.now().minusDays(30), LocalDate.now());
    }

    @PutMapping("/kasjerzy/{id}")
    PracownikDTO changeToInstruktor(@PathVariable Long id) {
        Instruktor instruktor = kasjerService.changeToInstruktor(id);
        return PracownikDTO.fromPracownik(instruktor);
    }
}

package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.WejscieDTO;
import org.example.projekt_mas.model.Wejscie;
import org.example.projekt_mas.service.WejscieService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class WejscieController {
    private final WejscieService wejscieService;

    public WejscieController(WejscieService wejscieService) {
        this.wejscieService = wejscieService;
    }

    @GetMapping("/wejscia/{id}/czyjednorazowe")
    boolean czyJednorazowe(@PathVariable Long id) {
        return wejscieService.czyJednorazowe(id);
    }

    @GetMapping("/klient/{id}/wejscia")
    Set<WejscieDTO> wyswietlWejscia(@PathVariable Long id) {
        Set<Wejscie> wejscia = wejscieService.wyswietlWejscia(id);
        Set<WejscieDTO> dtos = new HashSet<>();
        for(Wejscie wejscie : wejscia) {
            dtos.add(WejscieDTO.from(wejscie));
        }

        return dtos;
    }

    @PutMapping("/wejscia")
    int przetworzWejscie(@RequestBody Long klient_id) {
        Wejscie wejscie = wejscieService.przetworzWejscie(klient_id);
        if(wejscie == null) {
            return 0;
        }
        return wejscie.getId();
    }
}


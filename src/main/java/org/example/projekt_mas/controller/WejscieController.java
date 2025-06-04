package org.example.projekt_mas.controller;

import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.OsobaType;
import org.example.projekt_mas.model.Wejscie;
import org.example.projekt_mas.repository.OsobaRepository;
import org.example.projekt_mas.repository.WejscieRepository;
import org.example.projekt_mas.service.WejscieService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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

class WejscieDTO {
    public int id;
    public LocalDateTime czas_wejscia;
    public double cena_wejscia;
    public int klient_id;

    public static WejscieDTO from(Wejscie wejscie) {
        WejscieDTO dto = new WejscieDTO();
        dto.id = wejscie.getId();
        dto.czas_wejscia = wejscie.getCzas_wejscia();
        dto.cena_wejscia = wejscie.getCena_wejscia();
        dto.klient_id = wejscie.getKlient().getId();
        return dto;
    }
}
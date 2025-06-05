package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.KarnetDTO;
import org.example.projekt_mas.DTOs.KlientDTO;
import org.example.projekt_mas.DTOs.RegisterClientDTO;
import org.example.projekt_mas.service.OsobaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OsobaController {
    private final OsobaService osobaService;

    OsobaController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    @GetMapping("/osoby/{klient_id}/karnety")
    List<KarnetDTO> getKarnets(@PathVariable Long klient_id) {
        return KarnetDTO.fromKarnetSet(osobaService.getKarnets(klient_id));
    }

    @PostMapping("/osoby")
    KlientDTO newKlient(@RequestBody RegisterClientDTO klient_data) {
        return KlientDTO.fromKlient(osobaService.zarejestrujKlienta(klient_data.imie, klient_data.nazwisko, klient_data.email));
    }

    @GetMapping("/osoby/{id}/karnet-status")
    boolean czyAktywnyKarnet(@PathVariable Long id) {
        return osobaService.aktywnyKarnet(id);
    }
}


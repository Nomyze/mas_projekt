package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.KarnetDTO;
import org.example.projekt_mas.DTOs.KlientDTO;
import org.example.projekt_mas.DTOs.OsobaDTO;
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

    @GetMapping("/klienci/{klient_id}/karnety")
    List<KarnetDTO> getKarnets(@PathVariable Long klient_id) {
        return KarnetDTO.fromKarnetSetToKarnetWithMeta(osobaService.getKarnets(klient_id));
    }

    @PostMapping("/klienci")
    OsobaDTO newKlient(@RequestBody RegisterClientDTO klient_data) {
        return OsobaDTO.fromOsoba(osobaService.zarejestrujKlienta(klient_data.imie, klient_data.nazwisko, klient_data.email));
    }

    @GetMapping("/klienci/{id}/karnet-status")
    boolean czyAktywnyKarnet(@PathVariable Long id) {
        return osobaService.aktywnyKarnet(id);
    }

    @GetMapping("/klienci")
    List<KlientDTO> klienci() {
        return KlientDTO.fromListKlient(osobaService.getAllKlient());
    }
}


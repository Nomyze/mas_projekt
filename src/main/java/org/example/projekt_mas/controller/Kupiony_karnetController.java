package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.KasjerKarnetDTO;
import org.example.projekt_mas.DTOs.Kupiony_karnetDTO;
import org.example.projekt_mas.model.Kupiony_karnet;
import org.example.projekt_mas.service.Kupiony_karnetService;
import org.springframework.web.bind.annotation.*;

@RestController
public class Kupiony_karnetController {
    private final Kupiony_karnetService kupiony_karnetService;

    Kupiony_karnetController(Kupiony_karnetService kupiony_karnetService) {
        this.kupiony_karnetService = kupiony_karnetService;
    }

    @PostMapping("/klienci/{id}/karnety")
    public Kupiony_karnetDTO kupKarnet(@PathVariable Long id, @RequestBody KasjerKarnetDTO kasjerKarnetDTO) {
        Kupiony_karnet kk = kupiony_karnetService.kupKarnet(id, kasjerKarnetDTO.karnet_id, kasjerKarnetDTO.kasjer_id);
        return Kupiony_karnetDTO.fromKupionyKarnet(kk);
    }

    @PutMapping("/karnety/{karnet_id}")
    public Kupiony_karnetDTO przedluzKarnet(@PathVariable Long karnet_id) {
        Kupiony_karnet kk = kupiony_karnetService.przedluzKarnet(karnet_id);
        return Kupiony_karnetDTO.fromKupionyKarnet(kk);
    }
}

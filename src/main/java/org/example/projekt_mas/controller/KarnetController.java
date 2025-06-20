package org.example.projekt_mas.controller;

import org.example.projekt_mas.DTOs.BaseKarnetDTO;
import org.example.projekt_mas.service.KarnetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
public class KarnetController {
    private final KarnetService karnetService;

    KarnetController(KarnetService karnetService) {
        this.karnetService = karnetService;
    }

    @GetMapping("/karnety")
    public List<BaseKarnetDTO> getKarnety() {
        return BaseKarnetDTO.fromKarnetSet(new HashSet<>(karnetService.getAllKarnets()));
    }
}

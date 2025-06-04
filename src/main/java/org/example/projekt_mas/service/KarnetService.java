package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Karnet;
import org.example.projekt_mas.repository.KarnetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarnetService {
    private final KarnetRepository karnetRepository;

    public KarnetService(KarnetRepository karnetRepository) {
        this.karnetRepository = karnetRepository;
    }

    public List<Karnet> getAllKarnets() {
        return karnetRepository.findAll();
    }
}

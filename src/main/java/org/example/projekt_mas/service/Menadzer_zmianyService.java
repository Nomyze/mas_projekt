package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Menadzer_zmiany;
import org.example.projekt_mas.model.Uwaga;
import org.example.projekt_mas.repository.Menadzer_zmianyRepository;
import org.example.projekt_mas.repository.UwagaRepository;
import org.springframework.stereotype.Service;

@Service
public class Menadzer_zmianyService {
    private final Menadzer_zmianyRepository menadzer_zmianyRepository;
    private final UwagaRepository uwagaRepository;

    public Menadzer_zmianyService(Menadzer_zmianyRepository menadzer_zmianyRepository, UwagaRepository uwagaRepository) {
        this.menadzer_zmianyRepository = menadzer_zmianyRepository;
        this.uwagaRepository = uwagaRepository;
    }

    public Uwaga dodajUwage(long menadzer_id, String opis) {
        Menadzer_zmiany menadzer = menadzer_zmianyRepository.findById(menadzer_id).orElse(null);
        if (menadzer == null) {
            throw new IllegalArgumentException("Menadzer id " + menadzer_id + " not found");
        }

        Uwaga uwaga =  Uwaga.builder()
                .opis(opis)
                .zmiana(menadzer.getPrzewodzi())
                .build();
        uwagaRepository.save(uwaga);
        return uwaga;
    }
}

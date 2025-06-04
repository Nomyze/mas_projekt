package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Instruktor;
import org.example.projekt_mas.model.Kasjer;
import org.example.projekt_mas.model.Zajecia;
import org.example.projekt_mas.repository.InstruktorRepository;
import org.example.projekt_mas.repository.KasjerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InstruktorService {
    private final KasjerRepository kasjerRepository;
    InstruktorRepository instruktorRepository;

    public InstruktorService(InstruktorRepository instruktorRepository, KasjerRepository kasjerRepository) {
        this.instruktorRepository = instruktorRepository;
        this.kasjerRepository = kasjerRepository;
    }

    public double obliczWynagrodzenie(long instruktor_id, LocalDate dataOd, LocalDate dataDo) {
        Instruktor instruktor = instruktorRepository.findById(instruktor_id).orElse(null);
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor with id " + instruktor_id + " not found");
        }
        double sum = 0;
        for(Zajecia z : instruktor.getZajecia()) {
            if(z.getCzasStart().isAfter(dataOd.atStartOfDay()) && z.getCzasStart().isAfter(dataDo.atStartOfDay())) {
                sum += z.getStawkaInstruktora();
            }
        }
        return sum;
    }

    public Kasjer changeToKasjer(long instruktor_id) {
        Instruktor instruktor = instruktorRepository.findById(instruktor_id).orElse(null);
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor with id " + instruktor_id + " not found");
        }
        Kasjer kasjer = Kasjer.builder()
                .imie(instruktor.getImie())
                .nazwisko(instruktor.getNazwisko())
                .email(instruktor.getEmail())
                .osobaType(instruktor.getOsobaType())
                .stawkaGodzinowa(instruktor.getStawkaGodzinowa())
                .zatrudnionyOd(instruktor.getZatrudnionyOd())
                .build();
        instruktorRepository.delete(instruktor);
        kasjerRepository.save(kasjer);
        return kasjer;
    }
}

package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Instruktor;
import org.example.projekt_mas.model.Kasjer;
import org.example.projekt_mas.model.Zmiana;
import org.example.projekt_mas.repository.InstruktorRepository;
import org.example.projekt_mas.repository.KasjerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KasjerService {
    private final KasjerRepository kasjerRepository;
    private final InstruktorRepository instruktorRepository;

    public KasjerService(KasjerRepository kasjerRepository,
                         InstruktorRepository instrumentRepository) {
        this.kasjerRepository = kasjerRepository;
        this.instruktorRepository = instrumentRepository;
    }

    public Instruktor changeToInstruktor(long kasjer_id) {
        Kasjer kasjer = kasjerRepository.findById(kasjer_id).orElse(null);
        if (kasjer == null) {
            throw new IllegalArgumentException("Kasjer with id " + kasjer_id + " not found");
        }
        Instruktor instruktor = Instruktor.builder()
                .imie(kasjer.getImie())
                .nazwisko(kasjer.getNazwisko())
                .email(kasjer.getEmail())
                .osobaType(kasjer.getOsobaType())
                .stawkaGodzinowa(kasjer.getStawkaGodzinowa())
                .zatrudnionyOd(kasjer.getZatrudnionyOd())
                .build();
        kasjerRepository.delete(kasjer);
        instruktorRepository.save(instruktor);
        return instruktor;
    }
    public double obliczWynagrodzenie(long kasjer_id, LocalDate dataOd, LocalDate dataDo) {
        Kasjer kasjer = kasjerRepository.findById(kasjer_id).orElse(null);
        if (kasjer == null) {
            throw new IllegalArgumentException("Kasjer with id " + kasjer_id + " not found");
        }
        double sum = 0;
        for(Zmiana z : kasjer.getZmiany()) {
            if(z.getData().isAfter(dataOd) && z.getData().isAfter(dataDo)) {
                sum += 4 * kasjer.getStawkaGodzinowa();
            }
            if(z.getMenadzer().getPracownik().equals(kasjer)) {
                sum += z.getMenadzer().getPremiaHistoryczna();
            }
        }
        return sum;
    }

    public Kasjer createKasjer(String imie, String nazwisko, String email, double stawka, LocalDate data) {
        Kasjer kasjer = Kasjer.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .email(email)
                .stawkaGodzinowa(stawka)
                .zatrudnionyOd(data)
                .build();
        return kasjerRepository.save(kasjer);
    }
}

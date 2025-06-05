package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Kasjer;
import org.example.projekt_mas.model.Menadzer_zmiany;
import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.Zmiana;
import org.example.projekt_mas.repository.Menadzer_zmianyRepository;
import org.example.projekt_mas.repository.ZmianaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ZmianaService {
    private final ZmianaRepository zmianaRepository;
    private final Menadzer_zmianyRepository menadzer_zmianyRepository;
    public ZmianaService(ZmianaRepository zmianaRepository,
                         Menadzer_zmianyRepository menadzer_zmianyRepository) {
        this.zmianaRepository = zmianaRepository;
        this.menadzer_zmianyRepository = menadzer_zmianyRepository;
    }

    public Zmiana createZmiana(Kasjer menadzer, Set<Osoba> pracownicy, LocalDate data, int numerZmiany) {
        if(!pracownicy.contains(menadzer)) {
            throw new IllegalArgumentException("Menadzer to subset pracowników");
        }
        Menadzer_zmiany mz = Menadzer_zmiany.builder()
                .pracownik(menadzer)
                .build();
        menadzer_zmianyRepository.save(mz);
        Zmiana z = Zmiana.builder()
                .data(data)
                .numerZmiany(numerZmiany)
                .pracownicy(pracownicy)
                .menadzer(mz)
                .build();
        mz.setPrzewodzi(z);
        zmianaRepository.save(z);
        menadzer_zmianyRepository.save(mz);
        return z;
    }
}

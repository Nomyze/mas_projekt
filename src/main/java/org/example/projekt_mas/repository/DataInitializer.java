package org.example.projekt_mas.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.projekt_mas.model.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final KarnetRepository karnetRepository;
    private final OsobaRepository osobaRepository;
    private final ZajeciaRepository zajeciaRepository;
    private final Kupiony_karnetRepository kupionyKarnetRepository;
    private final WejscieRepository wejscieRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void atStart(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Initializing Data");
        Karnet karnet = Karnet.builder()
                .cena(300.0)
                .dlugosc(30)
                .build();
        karnetRepository.save(karnet);
        Osoba klient1 = Osoba.builder()
                .imie("Imie1")
                .nazwisko("Nazwisko1")
                .email("email1@example.com")
                .build();
        klient1.addOsobaType(OsobaType.Klient);
        Kupiony_karnet kk = Kupiony_karnet.builder()
                .klient(klient1)
                .karnet(karnet)
                .dataOd(LocalDate.now())
                .build();
        osobaRepository.save(klient1);
        kupionyKarnetRepository.save(kk);

        Wejscie wejscie = Wejscie.builder()
                .klient(klient1)
                .build();
        wejscieRepository.save(wejscie);
    }
}

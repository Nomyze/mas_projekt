package org.example.projekt_mas.repository;

import lombok.RequiredArgsConstructor;
import org.example.projekt_mas.model.*;
import org.example.projekt_mas.service.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ZmianaService zmianaService;
    private final KarnetService karnetService;
    private final OsobaService osobaService;
    private final KasjerService kasjerService;

    @EventListener(ContextRefreshedEvent.class)
    public void atStart(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Initializing Data");
        Karnet karnet = karnetService.createKarnet(300.0, 30);
        Karnet karnet2 = karnetService.createKarnet(500.0, 60);
        Osoba klient1 = osobaService.zarejestrujKlienta("Imie1", "Nazwisko1", "email1@example.org");
        //Kupiony_karnet kk = kupiony_karnetService.createKupiony_karnet(klient1.getId(), karnet.getId());

        //wejscieService.przetworzWejscie((long) klient1.getId());

        Kasjer kasjer = kasjerService.createKasjer("aaa", "bbb", "a@b.c", 3.0, LocalDate.now());
        Kasjer kasjer2 = kasjerService.createKasjer("aaa2", "bbb3", "a@b.c2", 3.0, LocalDate.now().plusDays(1));
        Kasjer kasjer3 = kasjerService.createKasjer("aaa3", "bbb3", "a@b.c3", 3.0, LocalDate.now().minusDays(1));

        zmianaService.createZmiana(kasjer, Set.of(kasjer, kasjer2, kasjer3), LocalDate.now(), 1);
    }
}

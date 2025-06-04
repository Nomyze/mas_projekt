package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Kupiony_karnet;
import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.OsobaType;
import org.example.projekt_mas.model.Wejscie;
import org.example.projekt_mas.repository.OsobaRepository;
import org.example.projekt_mas.repository.WejscieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class WejscieService {
    private final WejscieRepository wejscieRepository;
    private final OsobaService osobaService;

    WejscieService(WejscieRepository wejscieRepository,
                   OsobaService osobaService) {
        this.wejscieRepository = wejscieRepository;
        this.osobaService = osobaService;
    }

    public boolean czyJednorazowe(Long id) {
        Wejscie wejscie = wejscieRepository.findById(id).orElseThrow(RuntimeException::new);
        Set<Kupiony_karnet> setkk = new HashSet<>(wejscie.getKlient().getKupioneKarnety());
        return setkk.stream().noneMatch((k) -> {
            int dlugosc = k.getKarnet().getDlugosc() * k.getIloscKupiony();
            return LocalDate.now().isBefore(k.getDataOd().plusDays(dlugosc));
        });
    }

    public Set<Wejscie> wyswietlWejscia(Long id) {
        return wejscieRepository.findByKlientId(id);
    }

    public Wejscie przetworzWejscie(Long klient_id) {
        Osoba klient = osobaService.getOsoba(klient_id);
        if(klient == null) {
            return null;
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba ma byc klientem");
        }
        double cena_wejscia = 0.0;
        if(!osobaService.aktywnyKarnet(klient_id)) {
            cena_wejscia = Wejscie.cena_wejscia_jednorazowego;
        }
        System.out.println("Platnosc karta itd");
        Wejscie wejscie = Wejscie.builder()
                .cena_wejscia(cena_wejscia)
                .klient(klient)
                .build();
        wejscieRepository.save(wejscie);
        return wejscie;
    }
}

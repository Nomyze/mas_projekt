package org.example.projekt_mas.service;

import org.example.projekt_mas.model.*;
import org.example.projekt_mas.repository.Kupiony_karnetRepository;
import org.example.projekt_mas.repository.OsobaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class OsobaService {
    private final OsobaRepository osobaRepository;
    private final Kupiony_karnetRepository kupionyKarnetRepository;
    public OsobaService(OsobaRepository osobaRepository,
                        Kupiony_karnetRepository kupionyKarnetRepository) {
        this.osobaRepository = osobaRepository;
        this.kupionyKarnetRepository = kupionyKarnetRepository;
    }

    public Osoba getOsoba(long id) {
        return osobaRepository.findById(id).orElse(null);
    }
    public Osoba zarejestrujKlienta(String imie, String nazwisko, String email) {
        Osoba klient = Osoba.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .email(email)
                .czasRejestracji(LocalDateTime.now())
                .build();
        klient.addOsobaType(OsobaType.Klient);
        osobaRepository.save(klient);
        return klient;
    }
    public Osoba addPracownik(String imie, String nazwisko, String email, double stawka) {
        if(stawka > Instruktor.stawkaZaGodzineZajec) {
            throw new IllegalArgumentException("Stawka nie moze byc wyzsza niz dla instruktorów");
        }
        Osoba pracownik = Osoba.builder()
                .imie(imie)
                .nazwisko(nazwisko)
                .email(email)
                .stawkaGodzinowa(stawka)
                .zatrudnionyOd(LocalDate.now())
                .build();
        osobaRepository.save(pracownik);
        return pracownik;
    }
    public boolean aktywnyKarnet(long klient_id) {
        Osoba klient = osobaRepository.findById(klient_id).orElse(null);
        if (klient == null) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " not found");
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " is not a klient");
        }
        if(klient.getOsobaType().contains(OsobaType.Pracownik)) {
            return true;
        }
        return klient.getKupioneKarnety().stream().anyMatch((k) -> {
            return LocalDate.now().isBefore(k.getDataOd().plusDays((long) k.getKarnet().getDlugosc() * k.getIloscKupiony()));
        });
    }
    public Set<Karnet> getKarnets(long klient_id) {
        Osoba klient = osobaRepository.findById(klient_id).orElse(null);
        if (klient == null) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " not found");
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " is not a klient");
        }
        Set<Karnet> karnets = new HashSet<>();
        klient.getKupioneKarnety().stream().forEach((k) -> {karnets.add(k.getKarnet());});
        return karnets;
    }
}

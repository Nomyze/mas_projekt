package org.example.projekt_mas.service;

import org.example.projekt_mas.model.Instruktor;
import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.OsobaType;
import org.example.projekt_mas.model.Zajecia;
import org.example.projekt_mas.repository.InstruktorRepository;
import org.example.projekt_mas.repository.OsobaRepository;
import org.example.projekt_mas.repository.ZajeciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ZajeciaService {
    private final ZajeciaRepository zajeciaRepository;
    private final InstruktorRepository instruktorRepository;
    private final OsobaRepository osobaRepository;

    public ZajeciaService(ZajeciaRepository zajeciaRepository,
                          InstruktorRepository instruktorRepository,
                          OsobaRepository osobaRepository) {
        this.zajeciaRepository = zajeciaRepository;
        this.instruktorRepository = instruktorRepository;
        this.osobaRepository = osobaRepository;
    }

    public Zajecia utworzZajecia(long instruktor_id, LocalDateTime czasOd, double czasTrwania) {
        Instruktor instruktor = instruktorRepository.findById(instruktor_id).orElse(null);
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor with id " + instruktor_id + " not found");
        }
        if(czasOd.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("The past is in the past, leave it alone");
        }

        Zajecia zajecia = Zajecia.builder()
                .czasStart(czasOd)
                .czasTrwania(czasTrwania)
                .stawkaInstruktora(Instruktor.stawkaZaGodzineZajec)
                .instruktor(instruktor)
                .build();
        return zajeciaRepository.save(zajecia);
    }

    public void ustawInstruktora(long instruktor_id, long zajecia_id) {
        Instruktor instruktor = instruktorRepository.findById(instruktor_id).orElse(null);
        if (instruktor == null) {
            throw new IllegalArgumentException("Instruktor with id " + instruktor_id + " not found");
        }
        Zajecia zajecia = zajeciaRepository.findById(zajecia_id).orElse(null);
        if (zajecia == null) {
            throw new IllegalArgumentException("Zajecia with id " + zajecia_id + " not found");
        }
        zajecia.setInstruktor(instruktor);
        zajeciaRepository.save(zajecia);
    }

    public void dodajKlienta(long klient_id, long zajecia_id) {
        Osoba klient = osobaRepository.findById(klient_id).orElse(null);
        if (klient == null) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " not found");
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " is not a Klient");
        }
        Zajecia zajecia = zajeciaRepository.findById(zajecia_id).orElse(null);
        if (zajecia == null) {
            throw new IllegalArgumentException("Zajecia with id " + zajecia_id + " not found");
        }

        zajecia.getOsoby().add(klient);
        zajeciaRepository.save(zajecia);
    }

    public void usunKlienta(long klient_id, long zajecia_id) {
        Osoba klient = osobaRepository.findById(klient_id).orElse(null);
        if (klient == null) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " not found");
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " is not a Klient");
        }
        Zajecia zajecia = zajeciaRepository.findById(zajecia_id).orElse(null);
        if (zajecia == null) {
            throw new IllegalArgumentException("Zajecia with id " + zajecia_id + " not found");
        }
        if(!zajecia.getOsoby().contains(klient)) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " is not a member of Zajecia with id " + zajecia_id);
        }
        zajecia.getOsoby().remove(klient);
        zajeciaRepository.save(zajecia);
    }
}

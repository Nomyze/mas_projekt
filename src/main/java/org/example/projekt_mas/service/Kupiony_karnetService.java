package org.example.projekt_mas.service;

import org.example.projekt_mas.model.*;
import org.example.projekt_mas.repository.KarnetRepository;
import org.example.projekt_mas.repository.KasjerRepository;
import org.example.projekt_mas.repository.Kupiony_karnetRepository;
import org.example.projekt_mas.repository.OsobaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Kupiony_karnetService {
    private final Kupiony_karnetRepository kupionyKarnetRepository;
    private final OsobaRepository osobaRepository;
    private final KarnetRepository karnetRepository;
    private final KasjerRepository kasjerRepository;

    Kupiony_karnetService(Kupiony_karnetRepository kupionyKarnetRepository,
                          OsobaRepository osobaRepository,
                          KarnetRepository karnetRepository,
                          KasjerRepository kasjerRepository) {
        this.kupionyKarnetRepository = kupionyKarnetRepository;
        this.osobaRepository = osobaRepository;
        this.karnetRepository = karnetRepository;
        this.kasjerRepository = kasjerRepository;
    }

    public Kupiony_karnet createKupiony_karnet(long klient_id, long karnet_id) {
        Osoba klient = osobaRepository.findById(klient_id).orElse(null);
        if(klient == null) {
            throw new IllegalArgumentException("Osoba with id " + klient_id + " not found");
        }
        if(!klient.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba " + klient + " nie jest Klientem");
        }
        if(klient.getOsobaType().contains(OsobaType.Pracownik)) {
            throw new IllegalArgumentException("Osoba " + klient + "  jest Pracownikiem");
        }

        Karnet karnet = karnetRepository.findById(karnet_id).orElse(null);
        if(karnet == null) {
            throw new IllegalArgumentException("Karnet with id " + karnet_id + " not found");
        }
        Kupiony_karnet kupKar = Kupiony_karnet.builder()
                .klient(klient)
                .karnet(karnet)
                .build();
        return kupionyKarnetRepository.save(kupKar);
    }
    public Kupiony_karnet kupKarnet(long klient_id, long karnet_id, long kasjer_id) {
        Kasjer kasjer = kasjerRepository.findById(kasjer_id).orElse(null);
        if(kasjer == null) {
            throw new IllegalArgumentException("Kasjer with id " + kasjer_id + " not found");
        }
        kasjer.setSprzedaneKarnety(kasjer.getSprzedaneKarnety() + 1);
        kasjerRepository.save(kasjer);

        System.out.println("Platnosc za karnet");
        return createKupiony_karnet(klient_id, karnet_id);
    }

    public Kupiony_karnet przedluzKarnet(long kupiony_karnet_id) {
        Kupiony_karnet kk = kupionyKarnetRepository.findById(kupiony_karnet_id).orElse(null);
        if(kk == null) {
            throw new IllegalArgumentException("Kupiony " + kupiony_karnet_id + " not found");
        }
        System.out.println("Platnosc za przedluzenie karnetu");
        kk.setIloscKupiony(kk.getIloscKupiony() + 1);
        kupionyKarnetRepository.save(kk);
        return kk;
    }

    public void zawiadomKlienta(long kupiony_karnet_id) {
        Kupiony_karnet kk = kupionyKarnetRepository.findById(kupiony_karnet_id).orElse(null);
        if(kk == null) {
            throw new IllegalArgumentException("Kupiony " + kupiony_karnet_id + " not found");
        }

        if(kk.getDataOd().plusDays((long) kk.getKarnet().getDlugosc() * kk.getIloscKupiony())
                .isEqual(LocalDate.now().minusDays(3))) {
            System.out.println("Send notification about expiring karnet to: " + kk.getKlient().getEmail());
        }
    }
}

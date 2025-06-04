package org.example.projekt_mas.controller;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.projekt_mas.model.Karnet;
import org.example.projekt_mas.model.Kupiony_karnet;
import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.service.OsobaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
public class OsobaController {
    private final OsobaService osobaService;

    OsobaController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    @GetMapping("/osoba/{klient_id}/karnety")
    Set<KarnetDTO> getKarnets(@PathVariable Long klient_id) {
        Set<KarnetDTO> karnets = new HashSet<>();
        for(Karnet k : osobaService.getKarnets(klient_id)) {
            KarnetDTO karnetDTO = new KarnetDTO();
            karnetDTO.id = k.getId();
            karnetDTO.dlugosc = k.getDlugosc();
            karnetDTO.cena = k.getCena();
            karnetDTO.kupioneEgzemplarze = new HashSet<>();
            for(Kupiony_karnet kk : k.getKupioneEgzemplarze()) {
                Kupiony_karnetDTO kkDTO = new Kupiony_karnetDTO();
                kkDTO.id = kk.getId();
                kkDTO.dataOd = kk.getDataOd();
                kkDTO.dataDo = kk.getDataDo();
                kkDTO.iloscKupiony = kk.getIloscKupiony();
                kkDTO.klient_id = kk.getKarnet().getId();
                kkDTO.karnet_id = kk.getKarnet().getId();
                karnetDTO.kupioneEgzemplarze.add(kkDTO);
            }
            karnets.add(karnetDTO);
        }
        return karnets;
    }
}

class KarnetDTO {
    public long id;
    public Integer dlugosc;
    public Double cena;
    public Set<Kupiony_karnetDTO> kupioneEgzemplarze;
}

class Kupiony_karnetDTO {
    public long id;
    public LocalDate dataOd;
    public LocalDate dataDo;
    public Integer iloscKupiony;
    public long klient_id;
    public long karnet_id;
}
package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Kupiony_karnet;

import java.time.LocalDate;

public class Kupiony_karnetDTO {
    public long id;
    public LocalDate dataOd;
    public LocalDate dataDo;
    public Integer iloscKupiony;
    public long klient_id;
    public long karnet_id;

    public static Kupiony_karnetDTO fromKupionyKarnet(Kupiony_karnet kk) {
        Kupiony_karnetDTO kkDTO = new Kupiony_karnetDTO();
        kkDTO.id = kk.getId();
        kkDTO.dataDo = kk.getDataDo();
        kkDTO.dataOd = kk.getDataOd();
        kkDTO.iloscKupiony = kk.getIloscKupiony();
        kkDTO.klient_id = kk.getKlient().getId();
        kkDTO.karnet_id = kk.getKarnet().getId();
        return kkDTO;
    }
}

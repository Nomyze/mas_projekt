package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Karnet;
import org.example.projekt_mas.model.Kupiony_karnet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KarnetDTO extends BaseKarnetDTO{
    public Set<Kupiony_karnetDTO> kupioneEgzemplarze;

    public static List<KarnetDTO> fromKarnetSetToKarnetWithMeta(Set<Karnet> karnetList) {
        List<KarnetDTO> karnets = new ArrayList<>();
        for(Karnet k : karnetList) {
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
                kkDTO.klient_id = kk.getKlient().getId();
                kkDTO.karnet_id = kk.getKarnet().getId();
                karnetDTO.kupioneEgzemplarze.add(kkDTO);
            }
            karnets.add(karnetDTO);
        }
        return karnets;
    }
}

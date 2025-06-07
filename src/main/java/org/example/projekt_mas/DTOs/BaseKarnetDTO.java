package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Karnet;
import org.example.projekt_mas.model.Kupiony_karnet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BaseKarnetDTO {
    public long id;
    public Integer dlugosc;
    public Double cena;

    public static List<BaseKarnetDTO> fromKarnetSet(Set<Karnet> karnetList) {
        List<BaseKarnetDTO> karnets = new ArrayList<>();
        for(Karnet k : karnetList) {
            BaseKarnetDTO karnetDTO = new KarnetDTO();
            karnetDTO.id = k.getId();
            karnetDTO.dlugosc = k.getDlugosc();
            karnetDTO.cena = k.getCena();
            karnets.add(karnetDTO);
        }
        return karnets;
    }
}

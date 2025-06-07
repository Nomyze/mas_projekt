package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.OsobaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class KlientDTO extends OsobaDTO {
    public LocalDateTime czasRejestracji;

    public static KlientDTO fromKlient(Osoba osoba) {
        if(!osoba.getOsobaType().contains(OsobaType.Klient)) {
            throw new IllegalArgumentException("Osoba is not Klient type");
        }
        KlientDTO dto = new KlientDTO();
        dto.id = osoba.getId();
        dto.nazwisko = osoba.getNazwisko();
        dto.imie = osoba.getImie();
        dto.email = osoba.getEmail();
        dto.czasRejestracji = osoba.getCzasRejestracji();
        return dto;
    }

    public static List<KlientDTO> fromListKlient(List<Osoba> osoba) {
        List<KlientDTO> dtos = new ArrayList<>();
        for (Osoba o : osoba) {
            dtos.add(fromKlient(o));
        }
        return dtos;
    }
}

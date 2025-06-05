package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Osoba;

public class OsobaDTO {
    public int id;
    public String imie;
    public String nazwisko;
    public String email;

    public static OsobaDTO fromOsoba(Osoba osoba) {
        OsobaDTO dto = new OsobaDTO();
        dto.id = osoba.getId();
        dto.imie = osoba.getImie();
        dto.nazwisko = osoba.getNazwisko();
        dto.email = osoba.getEmail();
        return dto;
    }
}

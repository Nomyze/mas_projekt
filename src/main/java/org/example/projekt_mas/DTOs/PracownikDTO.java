package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Osoba;
import org.example.projekt_mas.model.OsobaType;

import java.time.LocalDate;

public class PracownikDTO extends OsobaDTO{
    public Double stawkaGodzinowa;
    public LocalDate zatrudnionyOd;

    public static PracownikDTO fromPracownik(Osoba pracownik) {
        if(!pracownik.getOsobaType().contains(OsobaType.Pracownik)) {
            throw new IllegalArgumentException("Osoba is not a pracownik");
        }
        PracownikDTO dto = new PracownikDTO();
        dto.id = pracownik.getId();
        dto.nazwisko = pracownik.getNazwisko();
        dto.imie = pracownik.getImie();
        dto.email = pracownik.getEmail();
        dto.stawkaGodzinowa = pracownik.getStawkaGodzinowa();
        dto.zatrudnionyOd = pracownik.getZatrudnionyOd();
        return dto;
    }
}

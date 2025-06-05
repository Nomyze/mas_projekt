package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Kasjer;

public class KasjerDTO extends PracownikDTO {
    public Integer sprzedaneKarnety;

    public static KasjerDTO fromKasjer(Kasjer kasjer) {
        KasjerDTO dto = new KasjerDTO();
        dto.stawkaGodzinowa = kasjer.getStawkaGodzinowa();
        dto.zatrudnionyOd = kasjer.getZatrudnionyOd();
        dto.id = kasjer.getId();
        dto.nazwisko = kasjer.getNazwisko();
        dto.imie = kasjer.getImie();
        dto.email = kasjer.getEmail();
        dto.sprzedaneKarnety = kasjer.getSprzedaneKarnety();
        return dto;
    }
}

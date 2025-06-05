package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Uwaga;

public class UwagaDTO {
    public String opis;
    public int zmiana_id;

    public static UwagaDTO from(Uwaga uwaga) {
        UwagaDTO dto = new UwagaDTO();
        dto.opis = uwaga.getOpis();
        dto.zmiana_id = uwaga.getZmiana().getId();
        return dto;
    }
}

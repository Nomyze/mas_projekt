package org.example.projekt_mas.DTOs;

import org.example.projekt_mas.model.Wejscie;

import java.time.LocalDateTime;

public class WejscieDTO {
    public int id;
    public LocalDateTime czas_wejscia;
    public double cena_wejscia;
    public int klient_id;

    public static WejscieDTO from(Wejscie wejscie) {
        WejscieDTO dto = new WejscieDTO();
        dto.id = wejscie.getId();
        dto.czas_wejscia = wejscie.getCzas_wejscia();
        dto.cena_wejscia = wejscie.getCena_wejscia();
        dto.klient_id = wejscie.getKlient().getId();
        return dto;
    }
}

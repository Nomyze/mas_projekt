package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menadzer_zmiany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Builder.Default
    @Min(0)
    private Double premiaHistoryczna = premiaZaZmiane;

    private static Double premiaZaZmiane = 40.0;

    @OneToOne
    @JoinColumn(name = "zmiana_id", referencedColumnName = "id")
    private Zmiana przewodzi;

    @ManyToOne
    @JoinColumn(name = "pracownik_id", nullable = false)
    @NotNull
    private Osoba pracownik;
}

package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wejscie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Builder.Default
    private LocalDateTime czas_wejscia = LocalDateTime.now();
    @Min(0)
    private double cena_wejscia;

    public static double cena_wejscia_jednorazowego = 45.0;

    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    private Osoba klient;
}

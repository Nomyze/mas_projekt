package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zajecia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime czasStart;

    @NotNull
    @Min(0)
    private Double czasTrwania;

    @NotNull
    @Min(0)
    private Double stawkaInstruktora;

    private static Double cenaZaGodzine = 50.0;
    private static Integer maxOsob = 12;

    @ManyToOne
    @JoinColumn(name = "instruktor_id", nullable = false)
    @NotNull
    private Instruktor instruktor;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "uczestnicy", joinColumns = @JoinColumn(name = "zajecia_id"), inverseJoinColumns = @JoinColumn(name = "klient_id"))
    @Size(min = 1, max = 12)
    private Set<Osoba> osoby;
}

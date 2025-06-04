package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zmiana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDate data;

    @NotNull
    @Min(0)
    @Max(3)
    private Integer numerZmiany;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "pracownicy", joinColumns = @JoinColumn(name = "zmiana_id"), inverseJoinColumns = @JoinColumn(name = "pracownik_id"))
    @Size(min = 2)
    private Set<Osoba> pracownicy;

    @OneToOne(mappedBy = "przewodzi")
    @NotNull
    private Menadzer_zmiany menadzer;

    @OneToMany(mappedBy = "zmiana", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Uwaga> uwagi;
}

package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.projekt_mas.Annotation.Subset;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Subset
public class Zmiana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @NotNull
    @Min(0)
    @Max(3)
    private Integer numerZmiany;

    @ManyToMany
    @JoinTable(name = "pracownicy", joinColumns = @JoinColumn(name = "zmiana_id"), inverseJoinColumns = @JoinColumn(name = "pracownik_id"))
    @Size(min = 2)
    @Builder.Default
    private Set<Osoba> pracownicy = new HashSet<>();

    @OneToOne(mappedBy = "przewodzi")
    @NotNull
    private Menadzer_zmiany menadzer;

    @OneToMany(mappedBy = "zmiana", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Uwaga> uwagi = new HashSet<>();
}

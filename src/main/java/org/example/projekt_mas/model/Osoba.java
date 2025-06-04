package org.example.projekt_mas.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Osoba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @NotNull
    private String imie;
    @NotBlank
    @NotNull
    private String nazwisko;
    @NotBlank
    @NotNull
    @Email
    private String email;

    @Builder.Default
    @ElementCollection(targetClass = OsobaType.class)
    @CollectionTable(name = "osoba_types", joinColumns = @JoinColumn(name = "osoba_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "osoba_type")
    private Set<OsobaType> osobaType = EnumSet.of(OsobaType.Osoba);

    @Nullable
    private LocalDateTime czasRejestracji;

    @Min(0)
    @Nullable
    private Double stawkaGodzinowa;

    @Nullable
    private LocalDate zatrudnionyOd;

    @OneToMany(mappedBy = "klient", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @Nullable
    private Set<Kupiony_karnet> kupioneKarnety = new HashSet<>();

    @ManyToMany(mappedBy = "osoby", cascade = CascadeType.REMOVE)
    @Nullable
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Zajecia> zajecia = new HashSet<>();

    @ManyToMany(mappedBy = "pracownicy", cascade = CascadeType.PERSIST)
    @Nullable
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Zmiana> zmiany = new HashSet<>();

    @OneToMany(mappedBy = "pracownik", cascade = CascadeType.REMOVE)
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Menadzer_zmiany> zarzadza = new HashSet<>();

    @OneToMany(mappedBy = "klient", cascade = CascadeType.REMOVE)
    @Nullable
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Wejscie> wejscia = new HashSet<>();

    public void addOsobaType(OsobaType osobaType) {
        this.osobaType.add(osobaType);
    }
}

package org.example.projekt_mas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.EnumSet;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"klient_id", "karnet_id"})
})
public class Kupiony_karnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Builder.Default
    private LocalDate dataOd = LocalDate.now();
    @NotNull
    @Min(1)
    @Builder.Default
    private Integer iloscKupiony = 1;

    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    private Osoba klient;

    @ManyToOne
    @JoinColumn(name = "karnet_id", nullable = false)
    @NotNull
    private Karnet karnet;

    public LocalDate getDataDo() {
        return dataOd.plusDays((long) karnet.getDlugosc() * iloscKupiony);
    }
}

package org.example.projekt_mas.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Kasjer extends Osoba {

    @Min(0)
    @Builder.Default
    @NotNull
    private Integer sprzedaneKarnety = 0;

    private static Double bonusZaKarnet = 20.0;
}

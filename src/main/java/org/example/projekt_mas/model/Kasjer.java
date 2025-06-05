package org.example.projekt_mas.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Kasjer extends Osoba {

    @Min(0)
    @Builder.Default
    @NotNull
    private int sprzedaneKarnety = 0;

    private static Double bonusZaKarnet = 20.0;
}

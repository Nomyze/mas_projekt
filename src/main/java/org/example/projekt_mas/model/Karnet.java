package org.example.projekt_mas.model;

import io.swagger.v3.oas.annotations.extensions.Extension;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Karnet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    private Integer dlugosc;
    @Min(0)
    private Double cena;

    @OneToMany(mappedBy = "karnet", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Kupiony_karnet> kupioneEgzemplarze;
}

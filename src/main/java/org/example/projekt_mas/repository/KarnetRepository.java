package org.example.projekt_mas.repository;

import org.example.projekt_mas.model.Karnet;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface KarnetRepository extends ListCrudRepository<Karnet, Long> {
}

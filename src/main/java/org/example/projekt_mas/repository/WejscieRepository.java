package org.example.projekt_mas.repository;

import org.example.projekt_mas.model.Wejscie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface WejscieRepository extends CrudRepository<Wejscie, Long> {
    @Query("from Wejscie as w where w.klient.id = :id")
    public Set<Wejscie> findByKlientId(@Param("id") Long id);
}

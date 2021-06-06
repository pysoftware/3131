package ru.sazonov.future1.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sazonov.future1.enteties.Planet;

import java.util.Set;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {

    @EntityGraph(attributePaths = {"lord"})
    Set<Planet> findAll();

}

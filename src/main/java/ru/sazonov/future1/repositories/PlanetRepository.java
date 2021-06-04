package ru.sazonov.future1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sazonov.future1.enteties.Planet;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {

}

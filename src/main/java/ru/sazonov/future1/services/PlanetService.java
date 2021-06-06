package ru.sazonov.future1.services;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.mappers.PlanetMapper;
import ru.sazonov.future1.repositories.PlanetRepository;
import ru.sazonov.future1.requests.PlanetModel;

import java.util.Set;

@Service
@AllArgsConstructor
public class PlanetService {
    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    public void deletePlanetById(Long planetId) {
        try {
            planetRepository.deleteById(planetId);
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundEntityException(exception.getMessage());
        }
    }

    public void createPlanet(PlanetModel planetModel) {
        Planet planet = planetMapper.toPlanetEntity(planetModel);
        planetRepository.save(planet);
    }

    public Set<Planet> findAll() {
        return planetRepository.findAll();
    }
}

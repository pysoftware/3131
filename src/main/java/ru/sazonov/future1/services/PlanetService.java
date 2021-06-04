package ru.sazonov.future1.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.mappers.PlanetMapper;
import ru.sazonov.future1.repositories.PlanetRepository;
import ru.sazonov.future1.requests.PlanetModel;

@Service
@AllArgsConstructor
public class PlanetService {
    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;

    public void deletePlanetById(long planetId) {
        planetRepository.deleteById(planetId);
    }

    public void createPlanet(PlanetModel planetModel) {
        Planet planet = planetMapper.toPlanetEntity(planetModel);
        planetRepository.save(planet);
    }

    public Iterable<Planet> findAll() {
        return planetRepository.findAll();
    }
}

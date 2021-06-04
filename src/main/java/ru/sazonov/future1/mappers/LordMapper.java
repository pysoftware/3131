package ru.sazonov.future1.mappers;

import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.repositories.PlanetRepository;
import ru.sazonov.future1.requests.LordModel;

import java.util.*;

@Mapper(componentModel = "spring")
@Slf4j
public abstract class LordMapper {

    @Autowired
    private PlanetRepository planetRepository;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "age", source = "age"),
            @Mapping(target = "planets", source = "planetIds", qualifiedByName = "toPlanetsSet")
    })
    public abstract Lord toLordEntity(LordModel planetModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "planets", source = "planets")
    public abstract void updateLord(Lord source, @MappingTarget Lord target);

    public Set<Planet> toPlanetsSet(Set<Long> planetIds) {
        if (Objects.isNull(planetIds)) {
            return Collections.emptySet();
        }
        Set<Planet> planets = new HashSet<>();
        planetIds.forEach(planetId -> {
            Optional<Planet> planet = planetRepository.findById(planetId);
            if (!planet.isPresent()) {
                throw new NotFoundEntityException(String.format("Planet with id: %d not found", planetId));
            } else {
                planets.add(planet.get());
            }
        });
        return planets;
    }
}

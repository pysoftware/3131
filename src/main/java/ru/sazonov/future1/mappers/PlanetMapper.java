package ru.sazonov.future1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.repositories.LordRepository;
import ru.sazonov.future1.requests.PlanetModel;

import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class PlanetMapper {

    @Autowired
    LordRepository lordRepository;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "lord", source = "lordId", qualifiedByName = "toLordById")
    })
    public abstract Planet toPlanetEntity(PlanetModel planetModel);

    protected Lord toLordById(Long lordId) {
        return lordRepository.findById(lordId).orElseThrow(() ->
                new NotFoundEntityException(String.format("Lord with id: %d not found", lordId)));
    }

}

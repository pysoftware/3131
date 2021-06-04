package ru.sazonov.future1.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.requests.PlanetModel;

@Mapper
public interface PlanetMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "name", source = "name"),
    })
    Planet toPlanetEntity(PlanetModel planetModel);

}

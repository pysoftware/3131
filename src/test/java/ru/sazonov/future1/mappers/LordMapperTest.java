package ru.sazonov.future1.mappers;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.requests.LordModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public class LordMapperTest extends AbstractTest {

    @SpyBean
    private LordMapper mapper;

    @Test
    public void Should_MapLordModelToLord_When_PlanetsIdsInDb() {
        LordModel lordModel = LordModel.builder()
                .name("name")
                .age(10L)
                .planetIds(new HashSet<>(Arrays.asList(1L, 2L)))
                .build();
        Planet planet1 = Planet.builder().id(1L).build();
        Planet planet2 = Planet.builder().id(2L).build();
        when(planetRepository.findById(1L)).thenReturn(Optional.of(planet1));
        when(planetRepository.findById(2L)).thenReturn(Optional.of(planet2));

        Lord lord = mapper.toLordEntity(lordModel);

        assertEquals(lord.getName(), lordModel.getName());
        assertEquals(lord.getAge(), lordModel.getAge());
        assertEquals(lord.getPlanets().size(), 2);
    }

    @Test(expected = NotFoundEntityException.class)
    public void Should_ThrowNotFoundEntityException_When_PlanetsIdsNotInDb() {
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());

        mapper.toLordEntity(LordModel
                .builder()
                .planetIds(new HashSet<>(Collections.singletonList(1L)))
                .build()
        );
    }
}
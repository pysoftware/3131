package ru.sazonov.future1.services;

import org.junit.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.exceptions.NotFoundEntityException;
import ru.sazonov.future1.mappers.LordMapper;
import ru.sazonov.future1.requests.LordModel;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LordServiceTest extends AbstractTest {

    @SpyBean
    LordMapper lordMapper;

    @SpyBean
    LordService lordService;

    @Test
    public void createLord() {
//        Lord lord = lord();
//        when(lordRepository.save(any(Lord.class))).thenReturn(lord);
//        when(lordService.createLord(any(LordModel.class)));
////        when(lordRepository.save())
//        lordService.createLord();
    }

    @Test
    public void get10YoungestLords() {
    }

    @Test
    public void findAllSlackerLords() {
    }

    @Test
    public void updateLord() {

    }

    @Test
    public void addPlanetToLord() {
        Lord lord = Lord.builder().id(1L).build();
        when(lordRepository.findById(1L)).thenReturn(Optional.of(lord));
        Planet planet = Planet.builder().id(1L).build();
        when(planetRepository.findById(1L)).thenReturn(Optional.of(planet));

//        LordModel lordModel = LordModel.builder().planetIds();
    }

    @Test(expected = NotFoundEntityException.class)
    public void whenAddPlanetToLordPlanetNotFoundInDb() {
        LordModel lordModel = LordModel.builder().planetIds(new HashSet<>(Collections.singletonList(1L))).build();
        Lord lord = Lord.builder().id(1L).planets(Collections.emptySet()).build();
        when(lordRepository.findById(1L)).thenReturn(Optional.of(lord));
        when(planetRepository.findById(1L)).thenReturn(Optional.empty());
        lordService.updateLord(1L, lordModel);
    }

    @Test
    public void findLordById() {
        Lord lord = Lord.builder().id(1L).build();
        when(lordRepository.findById(1L)).thenReturn(Optional.of(lord));

        Lord foundLord = lordService.findLordById(1L);

        assertNotNull(foundLord);
        assertEquals(1L, foundLord.getId());
    }

    @Test(expected = NotFoundEntityException.class)
    public void findLordById_andThenThrowNotFoundEntityException() {
        lordService.findLordById(1L);
    }
}
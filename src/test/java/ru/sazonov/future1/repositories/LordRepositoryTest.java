package ru.sazonov.future1.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sazonov.future1.AbstractTest;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LordRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    LordRepository lordRepository;

    @Autowired
    PlanetRepository planetRepository;

    @Test
    public void Should_CreateNewLord() {
        Lord lord = Lord.builder().name("name").age(10L).planets(new HashSet<>()).build();
        Planet newPlanet = Planet.builder().build();
        lord.addPlanet(newPlanet);
        lordRepository.save(lord);
        Lord savedLord = lordRepository.findById(lord.getId()).get();
        Planet savedPlanet = planetRepository.findById(newPlanet.getId()).get();
        assertNotNull(savedLord);
        assertNotNull(savedPlanet);
        assertEquals(savedLord.getId(), lord.getId());
        assertEquals(savedLord.getPlanets().size(), 1);
        assertEquals(savedPlanet.getLord(), savedLord);
    }

    @Test
    public void Should_FindByIdLord() {
        Lord lord = Lord.builder().name("name").age(10L).planets(new HashSet<>()).build();
        lordRepository.save(lord);
        Lord savedLord = lordRepository.findById(lord.getId()).get();

        assertNotNull(savedLord);
        assertEquals(savedLord.getId(), lord.getId());
    }
}
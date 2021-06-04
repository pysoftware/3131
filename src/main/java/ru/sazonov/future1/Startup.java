package ru.sazonov.future1;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.sazonov.future1.enteties.Lord;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.repositories.LordRepository;
import ru.sazonov.future1.repositories.PlanetRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class Startup implements CommandLineRunner {
    private final PlanetRepository planetRepository;
    private final LordRepository lordRepository;

    @Override
    public void run(String... args) throws Exception {
        Planet planet1 = new Planet();
        planet1.setName("plan1");
        Planet planet2 = new Planet();
        planet2.setName("plan2");
        Planet planet3 = new Planet();
        planet3.setName("plan3");

//        planetRepository.save(planet1);
//        planetRepository.save(planet2);
//        planetRepository.save(planet3);

        Lord lord1 = new Lord();
        lord1.setName("lord1");
        lord1.setAge(12L);
        Lord lord2 = new Lord();
        lord2.setAge(1000L);
        lord2.setName("lord2");
        Lord lord3 = new Lord();
        lord3.setName("lord3");
        lord3.setAge(1001L);

        for (int i = 0; i < 10; i++) {
            Lord tempLord = new Lord();
            tempLord.setAge((long) i);
            tempLord.setName("Lord #" + i);
            tempLord.setPlanets(Collections.emptySet());
            lordRepository.save(tempLord);
        }

        lord1.setPlanets(Collections.emptySet());
        Set<Planet> lord2Planets = new HashSet<>();
        lord2Planets.add(planet1);
        lord2Planets.add(planet2);
        lord2.setPlanets(lord2Planets);

        Set<Planet> lord3Planets = new HashSet<>();
        lord3Planets.add(planet3);
        lord3.setPlanets(lord3Planets);

        lordRepository.save(lord1);
        lordRepository.save(lord2);
        lordRepository.save(lord3);

    }
}

package ru.sazonov.future1.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sazonov.future1.enteties.Planet;
import ru.sazonov.future1.requests.PlanetModel;
import ru.sazonov.future1.services.PlanetService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planets")
@AllArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @PostMapping
    public ResponseEntity<?> createPlanet(@Valid @ModelAttribute PlanetModel planet) {
        planetService.createPlanet(planet);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(planetService.findAll());
    }
}

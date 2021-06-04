package ru.sazonov.future1.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lords")
@JsonIgnoreProperties({"planets"})
public class Lord extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "age")
    @Getter
    @Setter
    private Long age;

    @OneToMany(mappedBy = "lord", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    private Set<Planet> planets;

    public void setPlanets(Set<Planet> planets) {
        planets.forEach(planet -> planet.setLord(this));
        this.planets = planets;
    }

    public void addPlanet(Planet planet) {
        this.planets.add(planet);
        planet.setLord(this);
    }
}

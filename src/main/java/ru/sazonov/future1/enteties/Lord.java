package ru.sazonov.future1.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "lord", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    private Set<Planet> planets = new HashSet<>();

    public void setPlanets(Set<Planet> planets) {
        planets.forEach(planet -> planet.setLord(this));
        this.planets = planets;
    }

    public void addPlanet(Planet planet) {
        this.planets.add(planet);
        planet.setLord(this);
    }

    public void clearPlanets() {
        this.planets.forEach(item -> item.setLord(null));
        this.planets.clear();
    }
}

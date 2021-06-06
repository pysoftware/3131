package ru.sazonov.future1.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, exclude = {"planets"})
@Entity
@Table(name = "lords")
@Data
public class Lord extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Long age;

    @OneToMany(mappedBy = "lord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("lord")
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

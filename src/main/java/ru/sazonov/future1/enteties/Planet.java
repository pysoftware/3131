package ru.sazonov.future1.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true, exclude = {"lord"})
@Entity
@Table(name = "planets")
@Data
@SuperBuilder
@NoArgsConstructor
public class Planet extends BaseEntity {

    @Column(name = "name")
    private String name;

    /**
     * JoinTable cause of planet may have lord_id null
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "planet_lord",
            joinColumns = @JoinColumn(name = "planet_id"),
            inverseJoinColumns = @JoinColumn(name = "lord_id", nullable = false)
    )
    @JsonIgnoreProperties("planets")
    private Lord lord;
}

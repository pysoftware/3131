package ru.sazonov.future1.enteties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "planets")
public class Planet extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lord_id")
    @Getter
    @Setter
    private Lord lord;
}

package ru.sazonov.future1.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LordModel {

    @NotBlank
    private String name;

    @NotNull
    private Long age;

    private Set<Long> planetIds = new HashSet<>();
}

package de.jankahle.capstone.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {
    @EqualsAndHashCode.Exclude
    private String id;
    private String name;
    private List<Ingredient> ingredients;

    public DBRecipe toDBRecipe() {
        return DBRecipe.builder()
                .id(id)
                .name(name)
                .ingredients(ingredients)
                .build();
    }
}

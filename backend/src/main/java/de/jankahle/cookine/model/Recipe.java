package de.jankahle.cookine.model;

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

    public RecipeDao toDBRecipe() {
        return RecipeDao.builder()
                .id(id)
                .name(name)
                .ingredients(ingredients)
                .build();
    }
}

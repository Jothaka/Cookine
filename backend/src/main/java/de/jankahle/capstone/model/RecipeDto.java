package de.jankahle.capstone.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {
    @EqualsAndHashCode.Exclude
    private String id;
    private String name;
    private List<Ingredient> ingredients;

    public  Recipe toRecipe() {
        return  Recipe.builder()
                .id(id)
                .name(name)
                .ingredients(ingredients)
                .build();
    }
}

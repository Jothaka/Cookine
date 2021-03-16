package de.jankahle.capstone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeDto {
    private String name;
    private List<Ingredient> ingredients;

    public  Recipe toRecipe() {
        return  Recipe.builder()
                .name(name)
                .ingredients(ingredients)
                .build();
    }
}

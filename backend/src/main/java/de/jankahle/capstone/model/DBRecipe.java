package de.jankahle.capstone.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "recipes")
public class DBRecipe {
    @EqualsAndHashCode.Exclude
    @Id
    private String id;
    private String name;
    private List<Ingredient> ingredients;

    public Recipe toRecipe(){
        return Recipe.builder()
                .id(id)
                .name(name)
                .ingredients(ingredients)
                .build();
    }
}

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
}

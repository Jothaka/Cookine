package de.jankahle.capstone.service;

import de.jankahle.capstone.db.RecipeMongoDB;
import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeServiceTest {

    private final RecipeMongoDB recipeMongoDB = mock(RecipeMongoDB.class);
    private  final RecipeService recipeService = new RecipeService(recipeMongoDB);

    @Test
    @DisplayName("Save recipe to DB should return a Recipe equivalent of the RecipeDto")
    void saveRecipe() {
        //Given
        List<Ingredient> ingredients = List.of(
                Ingredient.builder().name("Kartoffel").amount(800).measurementUnit("Gramm").build(),
                Ingredient.builder().name("Salz").amount(1).measurementUnit("Prise").build()
        );

        RecipeDto recipeDto =
                RecipeDto.builder()
                        .name("Salzkartoffeln")
                        .ingredients(ingredients)
                        .build();

        //When
        Recipe actual = recipeService.saveRecipeToDB(recipeDto);

        //Then
        List<Ingredient> expectedIngredients = List.of(
                Ingredient.builder().name("Kartoffel").amount(800).measurementUnit("Gramm").build(),
                Ingredient.builder().name("Salz").amount(1).measurementUnit("Prise").build()
        );
        Recipe expected = Recipe.builder()
                .name("Salzkartoffeln")
                .ingredients(expectedIngredients)
                .build();

        assertThat(actual, Matchers.is(expected));
    }
}
package de.jankahle.capstone.service;

import de.jankahle.capstone.db.RecipeMongoDB;
import de.jankahle.capstone.model.DBRecipe;
import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    private final RecipeMongoDB recipeMongoDB = mock(RecipeMongoDB.class);
    private  final ImageReaderService readerService = mock(ImageReaderService.class);
    private  final  RecipeTextFilterService filterService = mock(RecipeTextFilterService.class);
    private final RecipeService recipeService = new RecipeService(recipeMongoDB, readerService, filterService);

    @Test
    @DisplayName("Save recipe to DB should return a Recipe equivalent of the RecipeDto")
    void saveRecipe() {
        //Given
        List<Ingredient> ingredients = createSaltedPotatoesIngredients();

        RecipeDto recipeDto =
                RecipeDto.builder()
                        .name("Salzkartoffeln")
                        .ingredients(ingredients)
                        .build();

        //When
        Recipe actual = recipeService.saveRecipe(recipeDto);

        //Then
        List<Ingredient> expectedIngredients = createSaltedPotatoesIngredients();
        Recipe expected = Recipe.builder()
                .name("Salzkartoffeln")
                .ingredients(expectedIngredients)
                .build();

        assertThat(actual, Matchers.is(expected));
        verify(recipeMongoDB).save(expected.toDBRecipe());
    }

    @Test
    @DisplayName("Load recipes from DB should return a List of Recipes equivalent to the saved ones")
    void loadRecipesFromDB() {
        //Given
        RecipeDto firstRecipeDto =
                RecipeDto.builder()
                        .name("Salzkartoffeln")
                        .ingredients(createSaltedPotatoesIngredients())
                        .build();

        RecipeDto secondRecipeDto =
                RecipeDto.builder()
                        .name("Pasta")
                        .ingredients(createPastaIngredients())
                        .build();

        recipeService.saveRecipe(firstRecipeDto);
        recipeService.saveRecipe(secondRecipeDto);

        DBRecipe firstDBRecipe =
                DBRecipe.builder()
                        .name("Salzkartoffeln")
                        .ingredients(createSaltedPotatoesIngredients())
                        .build();

        DBRecipe secondDBRecipe =
                DBRecipe.builder()
                        .name("Pasta")
                        .ingredients(createPastaIngredients())
                        .build();

        when(recipeMongoDB.findAll()).thenReturn(List.of(firstDBRecipe, secondDBRecipe));

        //When
        List<Recipe> actualList = recipeService.loadRecipes();

        //Then
        Recipe firstExpectedRecipe = Recipe.builder()
                .name("Salzkartoffeln")
                .ingredients(createSaltedPotatoesIngredients())
                .build();

        Recipe secondExpectedRecipe =
                Recipe.builder()
                        .name("Pasta")
                        .ingredients(createPastaIngredients())
                        .build();

        assertThat(actualList, Matchers.containsInAnyOrder(firstExpectedRecipe, secondExpectedRecipe));
    }

    private List<Ingredient> createPastaIngredients() {
        return List.of(
                Ingredient.builder().name("Nudeln").amount("100").measurementUnit("Gramm").build(),
                Ingredient.builder().name("Wasser").amount("1").measurementUnit("Liter").build(),
                Ingredient.builder().name("Salz").amount("7").measurementUnit("Gramm").build()
        );
    }

    private List<Ingredient> createSaltedPotatoesIngredients() {
        return List.of(
                Ingredient.builder().name("Kartoffel").amount("800").measurementUnit("Gramm").build(),
                Ingredient.builder().name("Salz").amount("1").measurementUnit("Prise").build()
        );
    }
}
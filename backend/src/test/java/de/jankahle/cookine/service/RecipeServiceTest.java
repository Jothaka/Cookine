package de.jankahle.cookine.service;

import de.jankahle.cookine.TestModelFactory;
import de.jankahle.cookine.db.RecipeDB;
import de.jankahle.cookine.model.Recipe;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    private final RecipeDB recipeDB = mock(RecipeDB.class);
    private  final ImageReaderService readerService = mock(ImageReaderService.class);
    private  final  RecipeTextFilterService filterService = mock(RecipeTextFilterService.class);
    private final RecipeService recipeService = new RecipeService(recipeDB, readerService, filterService);

    @Test
    @Tag("Unit")
    @DisplayName("Save recipe to DB should return a Recipe equivalent of the RecipeDto")
    void saveRecipe() {
        //When
        Recipe actual = recipeService.saveRecipe(TestModelFactory.createPotatoDto());

        //Then
        Recipe expected = TestModelFactory.createPotatoRecipe();

        assertThat(actual, Matchers.is(expected));
        verify(recipeDB).save(expected.toDBRecipe());
    }

    @Test
    @Tag("Unit")
    @DisplayName("Load recipes from DB should return a List of Recipes equivalent to the saved ones")
    void loadRecipesFromDB() {
        //Given
        recipeService.saveRecipe(TestModelFactory.createPotatoDto());
        recipeService.saveRecipe(TestModelFactory.createPastaDto());

        when(recipeDB.findAll()).thenReturn(List.of(
                TestModelFactory.createPotatoDBRecipe(),
                TestModelFactory.createPastaDBRecipe()));

        //When
        List<Recipe> actualList = recipeService.loadRecipes();

        //Then
        assertThat(actualList, Matchers.containsInAnyOrder(
                TestModelFactory.createPotatoRecipe(),
                TestModelFactory.createPastaRecipe()));

        verify(recipeDB).findAll();
    }
}
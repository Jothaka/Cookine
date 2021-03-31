package de.jankahle.capstone.service;

import de.jankahle.capstone.TestFactory;
import de.jankahle.capstone.db.RecipeDB;
import de.jankahle.capstone.model.Recipe;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Save recipe to DB should return a Recipe equivalent of the RecipeDto")
    void saveRecipe() {
        //When
        Recipe actual = recipeService.saveRecipe(TestFactory.createPotatoDto());

        //Then
        Recipe expected = TestFactory.createPotatoRecipe();

        assertThat(actual, Matchers.is(expected));
        verify(recipeDB).save(expected.toDBRecipe());
    }

    @Test
    @DisplayName("Load recipes from DB should return a List of Recipes equivalent to the saved ones")
    void loadRecipesFromDB() {
        //Given
        recipeService.saveRecipe(TestFactory.createPotatoDto());
        recipeService.saveRecipe(TestFactory.createPastaDto());

        when(recipeDB.findAll()).thenReturn(List.of(
                TestFactory.createPotatoDBRecipe(),
                TestFactory.createPastaDBRecipe()));

        //When
        List<Recipe> actualList = recipeService.loadRecipes();

        //Then
        assertThat(actualList, Matchers.containsInAnyOrder(
                TestFactory.createPotatoRecipe(),
                TestFactory.createPastaRecipe()));

        verify(recipeDB).findAll();
    }
}
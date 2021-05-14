package de.jankahle.cookine.controller;

import de.jankahle.cookine.db.RecipeDB;
import de.jankahle.cookine.model.Recipe;
import de.jankahle.cookine.model.RecipeDao;
import de.jankahle.cookine.model.RecipeDto;
import de.jankahle.cookine.TestModelFactory;
import de.jankahle.cookine.utility.IdUtility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeControllerTest {

    @LocalServerPort
    private int port;

    private String getUrl() {
        return "http://localhost:" + port + "api/recipe";
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RecipeDB recipeDB;

    @BeforeEach
    public  void setup() {
        recipeDB.deleteAll();
    }

    @Test
    @Tag("Integration")
    @DisplayName("Saving a new Recipe adds the Recipe to the DB")
    void saveRecipeTest() {
        //When
        HttpEntity<RecipeDto> postEntity = new HttpEntity<>(TestModelFactory.createPotatoDto());
        ResponseEntity<Recipe> actualResponse = testRestTemplate.postForEntity(getUrl(),postEntity,Recipe.class);

        //Then
        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), Matchers.is(TestModelFactory.createPotatoRecipe()));
        assertTrue(recipeDB.existsById(actualResponse.getBody().getId()));
    }

    @Test
    @Tag("Integration")
    @DisplayName("REST Delete should remove a recipe with a specified Id from the DB")
    void deleteRecipeTest() {
        //Given
        RecipeDao recipeToDelete = TestModelFactory.createPotatoDBRecipe();
        String uuid = IdUtility.generateId();
        recipeToDelete.setId(uuid);
        recipeDB.save(recipeToDelete);

        //When
        String deleteUrl = getUrl() + "/" +uuid;
        testRestTemplate.delete(deleteUrl);

        //Then
        assertFalse(recipeDB.existsById(uuid));
    }

    @Test
    @Tag("Integration")
    @DisplayName("loadRecipes should return all saved Recipes")
    void loadRecipesTest() {
        //Given
        recipeDB.save(TestModelFactory.createPotatoDBRecipe());
        recipeDB.save(TestModelFactory.createPastaDBRecipe());

        //When
        ResponseEntity<Recipe[]> actualResponse = testRestTemplate.getForEntity(getUrl(),Recipe[].class);

        //Then
        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), arrayContainingInAnyOrder(
                TestModelFactory.createPotatoRecipe(),
                TestModelFactory.createPastaRecipe()));
    }
}
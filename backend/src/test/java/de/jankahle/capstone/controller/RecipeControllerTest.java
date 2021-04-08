package de.jankahle.capstone.controller;

import de.jankahle.capstone.db.RecipeDB;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDao;
import de.jankahle.capstone.model.RecipeDto;
import de.jankahle.capstone.TestFactory;
import de.jankahle.capstone.utility.IdUtility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Saving a new Recipe adds the Recipe to the DB")
    void saveRecipeTest() {
        //When
        HttpEntity<RecipeDto> postEntity = new HttpEntity<>(TestFactory.createPotatoDto());
        ResponseEntity<Recipe> actualResponse = testRestTemplate.postForEntity(getUrl(),postEntity,Recipe.class);

        //Then
        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), Matchers.is(TestFactory.createPotatoRecipe()));
        assertTrue(recipeDB.existsById(actualResponse.getBody().getId()));
    }

    @Test
    @DisplayName("REST Delete should remove a recipe with a specified Id from the DB")
    void deleteRecipeTest() {
        //Given
        RecipeDao recipeToDelete = TestFactory.createPotatoDBRecipe();
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
    @DisplayName("loadRecipes should return all saved Recipes")
    void loadRecipesTest() {
        //Given
        recipeDB.save(TestFactory.createPotatoDBRecipe());
        recipeDB.save(TestFactory.createPastaDBRecipe());

        //When
        ResponseEntity<Recipe[]> actualResponse = testRestTemplate.getForEntity(getUrl(),Recipe[].class);

        //Then
        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), arrayContainingInAnyOrder(
                TestFactory.createPotatoRecipe(),
                TestFactory.createPastaRecipe()));
    }
}
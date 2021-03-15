package de.jankahle.capstone.controller;

import de.jankahle.capstone.db.RecipeMongoDB;
import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
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

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
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
    private RecipeMongoDB recipeMongoDB;

    @BeforeEach
    public  void setup() {
        recipeMongoDB.deleteAll();
    }

    @Test
    @DisplayName("Saving a new Recipe adds the Recipe to the DB")
    void saveRecipe() {
        //GIVEN
        List<Ingredient> ingredients = List.of(
                Ingredient.builder().name("Kartoffel").amount(800).measurementUnit("gram").build(),
                Ingredient.builder().name("Salz").amount(1).measurementUnit("Prise").build()
        );

        RecipeDto recipeDto =
                RecipeDto.builder()
                        .name("Salzkartoffeln")
                        .ingredients(ingredients)
                        .build();

        //WHEN
        HttpEntity<RecipeDto> postEntity = new HttpEntity<>(recipeDto);
        ResponseEntity<Recipe> actualResponse = testRestTemplate.postForEntity(getUrl(),postEntity,Recipe.class);


        //THEN
        Recipe expected = Recipe.builder()
                .name("Salzkartoffeln")
                .ingredients(ingredients)
                .build();

        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), Matchers.is(expected));
        assertTrue(recipeMongoDB.existsById(actualResponse.getBody().getId()));
    }
}
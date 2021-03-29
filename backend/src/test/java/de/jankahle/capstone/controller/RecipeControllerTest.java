package de.jankahle.capstone.controller;

import de.jankahle.capstone.db.RecipeMongoDB;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import de.jankahle.capstone.TestFactory;
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
        //WHEN
        HttpEntity<RecipeDto> postEntity = new HttpEntity<>(TestFactory.createPotatoDto());
        ResponseEntity<Recipe> actualResponse = testRestTemplate.postForEntity(getUrl(),postEntity,Recipe.class);

        //THEN
        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), Matchers.is(TestFactory.createPotatoRecipe()));
        assertTrue(recipeMongoDB.existsById(actualResponse.getBody().getId()));
    }
}
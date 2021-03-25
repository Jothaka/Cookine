package de.jankahle.capstone.controller;

import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileUploadControllerTest {

    @LocalServerPort
    private int port;

    private String getUrl() {
        return "http://localhost:" + port + "api/upload";
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("An uploaded image of an recipe returns a correct Recipe")
    @Test
    void parseFileToRecipeTest() {
        //Given
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new ClassPathResource("testrecept.png"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        //When
        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<Recipe> actualResponse = testRestTemplate.exchange(getUrl(), HttpMethod.POST, entity, Recipe.class);

        //Then
        Recipe expected = Recipe.builder()
                .ingredients(getTestRecipeIngredients())
                .name("")
                .build();

        assertThat(actualResponse.getStatusCode(), Matchers.is(HttpStatus.OK));
        assertThat(actualResponse.getBody(), Matchers.is(expected));
    }

    private List<Ingredient> getTestRecipeIngredients() {
        return List.of(
                Ingredient.builder()
                        .name("Kartoffeln, mehligkochend")
                        .amount("1")
                        .measurementUnit("kg")
                        .build(),
                Ingredient.builder()
                        .name("Knoblauchzehe")
                        .amount("1")
                        .measurementUnit("")
                        .build(),
                Ingredient.builder()
                        .name("Schlagsahne")
                        .amount("400")
                        .measurementUnit("ml")
                        .build(),
                Ingredient.builder()
                        .name("Parmesan, gerieben")
                        .amount("100")
                        .measurementUnit("g")
                        .build(),
                Ingredient.builder()
                        .name("Butter")
                        .amount("50")
                        .measurementUnit("g")
                        .build(),
                Ingredient.builder()
                        .name("Petersilie")
                        .amount("1/2")
                        .measurementUnit("Bund")
                        .build(),
                Ingredient.builder()
                        .name("JS Kartoffel Allrounder")
                        .amount("10")
                        .measurementUnit("Pr.")
                        .build(),
                Ingredient.builder()
                        .name("Salz")
                        .amount("3")
                        .measurementUnit("Prisen")
                        .build()
        );
    }
}
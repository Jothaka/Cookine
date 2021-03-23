package de.jankahle.capstone.service;

import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

class RecipeTextFilterServiceTest {

    private String testRecipeString = "a WÜURZIGES KARTOFFELGRATIN\n" +
            "4 ' ZUTATEN\n" +
            "a — .\n" +
            "> BD utspin © 4 ©\n" +
            "m Da -\n" +
            "Sn ill a m ir ie ie ir 8 (66) Rezept bewerten Personen\n" +
            "Pa | -\n" +
            "BZ .\n" +
            "z a 1kg Kartoffeln, mehligkochend\n" +
            "SU) EEE“ DD somn POT sim\n" +
            "1 MW 4 1 Knoblauchzehe\n" +
            "en i 400 ml Schlagsahne\n" +
            "Di 4 ©\n" +
            "z MS A 100 g Parmesan, gerieben\n" +
            "X Se f { 50 g Butter\n" +
            "N 1/2 Bund Petersilie\n" +
            "etwas Salz\n" +
            "ZUBEREITUNG\n" +
            "10 Pr. JS Kartoffel Allrounder\n" +
            "© Den Backofen auf 180 °C (Umluft) vorheizen. Die Kartoffeln schälen und in ca. 0,5 cm feine\n" +
            "Scheiben schneiden. Den Knoblauch ebenfalls schälen und halbieren.\n" +
            "© Die Sahne mit der Creme Fraiche, dem Knoblauch, dem Kartoffel Allrounder und 3 Prisen Salz in\n" +
            "einem Topf aufkochen lassen und vom Herd nehmen. Den Knoblauch herausnehmen und 50 g\n" +
            "Parmesan unterheben.\n" +
            "© Eine Auflaufform gleichmäßig mit der Butter einfetten und die Kartoffelscheiben darin leicht\n" +
            "überlappend aufschichten, mit der Sahne-Mischung übergießen und mit dem übrigen Parmesan\n" +
            "bestreuen.\n" +
            "0 Den Gratin ca. 50 Minuten im Ofen garen, bis es leicht gebräunt ist. Die Petersilie waschen,\n" +
            "trocken tupfen und fein schneiden. Den Kartoffelgratin mit der Petersilie bestreut servieren.\n";

    @Test
    void parseStringToRecipeTest() {
        //Given
        RecipeTextFilterService recipeTextFilterService = new RecipeTextFilterService();

        //When
        Recipe actual = recipeTextFilterService.parseStringToRecipe(testRecipeString);

        //Then


    }

    private List<Ingredient> getTestRecipeIngredients() {

        List<Ingredient> testIngredients = List.of(
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
        return List.of();
    }

    @DisplayName("A provided String should return a valid ingredient if there is one included")
    @ParameterizedTest
    @MethodSource
    void parseIngredientTest(String input, Optional<Ingredient> expected) {
        //Given
        RecipeTextFilterService recipeTextFilterService = new RecipeTextFilterService();

        //When
        Optional<Ingredient> actual = recipeTextFilterService.parseIngredient(input);

        //Then
        assertThat(actual.isEmpty(), Matchers.is(expected.isEmpty()));
        if (actual.isPresent() && expected.isPresent()) {
            assertThat(actual.get(), Matchers.is(expected.get()));
        }
    }

    static Stream<Arguments> parseIngredientTest() {
        String input1 = "z a 1kg Kartoffeln, mehligkochend";
        Ingredient expected1 = Ingredient.builder()
                .name("Kartoffeln, mehligkochend")
                .amount("1")
                .measurementUnit("kg")
                .build();

        String input2 = "1 MW 4 1 Knoblauchzehe";
        Ingredient expected2 = Ingredient.builder()
                .name("Knoblauchzehe")
                .amount("1")
                .measurementUnit("")
                .build();

        String input3 = "Scheiben schneiden. Den Knoblauch ebenfalls schälen und halbieren.";

        String input4 = "en i 400 ml Schlagsahne";
        Ingredient expected4 = Ingredient.builder()
                .name("Schlagsahne")
                .amount("400")
                .measurementUnit("ml")
                .build();

        String input5 = "N 1/2 Bund Petersilie";
        Ingredient expected5 = Ingredient.builder()
                .name("Petersilie")
                .amount("1/2")
                .measurementUnit("Bund")
                .build();

        String input6 = "10 Pr. JS Kartoffel Allrounder";
        Ingredient expected6 = Ingredient.builder()
                .name("JS Kartoffel Allrounder")
                .amount("10")
                .measurementUnit("Pr.")
                .build();

        String input7 = "0 Den Gratin ca. 50 Minuten im Ofen garen, bis es leicht gebräunt ist. Die Petersilie waschen,";
        String input8 = "Sn ill a m ir ie ie ir 8 (66) Rezept bewerten Personen";

        return Stream.of(
                Arguments.arguments(input1, Optional.of(expected1)),
                Arguments.arguments(input2, Optional.of(expected2)),
                Arguments.arguments(input3, Optional.empty()),
                Arguments.arguments(input4, Optional.of(expected4)),
                Arguments.arguments(input5, Optional.of(expected5)),
                Arguments.arguments(input6, Optional.of(expected6)),
                Arguments.arguments(input7, Optional.empty()),
                Arguments.arguments(input8, Optional.empty())
        );
    }
}
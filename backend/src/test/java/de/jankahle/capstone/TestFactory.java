package de.jankahle.capstone;

import de.jankahle.capstone.model.RecipeDao;
import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;

import java.util.List;

public class TestFactory {

    public static Recipe createPotatoRecipe() {
        return Recipe.builder()
                .name("Salzkartoffeln")
                .ingredients(createPotatoIngredientsList())
                .build();
    }

    public static RecipeDto createPotatoDto() {
        return RecipeDto.builder()
                .id("")
                .name("Salzkartoffeln")
                .ingredients(createPotatoIngredientsList())
                .build();
    }

    public static RecipeDao createPotatoDBRecipe() {
        return RecipeDao.builder()
                .name("Salzkartoffeln")
                .ingredients(createPotatoIngredientsList())
                .build();
    }

    public static List<Ingredient> createPotatoIngredientsList() {
        return List.of(
                Ingredient.builder().name("Kartoffel").amount("800").measurementUnit("Gramm").build(),
                Ingredient.builder().name("Salz").amount("1").measurementUnit("Prise").build()
        );
    }

    public static Recipe createPastaRecipe() {
        return Recipe.builder()
                .name("Pasta")
                .ingredients(createPastaIngredients())
                .build();
    }

    public static RecipeDao createPastaDBRecipe() {
        return RecipeDao.builder()
                .name("Pasta")
                .ingredients(createPastaIngredients())
                .build();
    }

    public static RecipeDto createPastaDto() {
        return RecipeDto.builder()
                .id("")
                .name("Pasta")
                .ingredients(createPastaIngredients())
                .build();
    }

    public static List<Ingredient> createPastaIngredients() {
        return List.of(
                Ingredient.builder().name("Nudeln").amount("100").measurementUnit("Gramm").build(),
                Ingredient.builder().name("Wasser").amount("1").measurementUnit("Liter").build(),
                Ingredient.builder().name("Salz").amount("7").measurementUnit("Gramm").build()
        );
    }
}

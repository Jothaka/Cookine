package de.jankahle.cookine.controller;

import de.jankahle.cookine.model.Recipe;
import de.jankahle.cookine.model.RecipeDto;
import de.jankahle.cookine.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @PostMapping
    public Recipe saveRecipe(@RequestBody RecipeDto recipeDto) {
        return recipeService.saveRecipe(recipeDto);
    }

    @GetMapping
    public List<Recipe> loadRecipes() {
        return recipeService.loadRecipes();
    }

    @DeleteMapping("{recipeId}")
    public void deleteRecipe(@PathVariable String recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}

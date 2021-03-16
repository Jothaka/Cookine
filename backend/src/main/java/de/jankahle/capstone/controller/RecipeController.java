package de.jankahle.capstone.controller;

import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import de.jankahle.capstone.service.RecipeService;
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
        return recipeService.saveRecipeToDB(recipeDto);
    }

    @GetMapping
    public List<Recipe> loadRecipes() {
        return recipeService.loadRecipesFromDB();
    }
}

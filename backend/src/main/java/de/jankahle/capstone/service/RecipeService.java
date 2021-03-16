package de.jankahle.capstone.service;

import de.jankahle.capstone.db.RecipeMongoDB;
import de.jankahle.capstone.model.DBRecipe;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import de.jankahle.capstone.utility.IdUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeMongoDB recipeMongoDB;

    @Autowired
    public RecipeService(RecipeMongoDB recipeMongoDB) {
        this.recipeMongoDB = recipeMongoDB;
    }

    public Recipe saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();
        recipe.setId(IdUtility.generateId());
        recipeMongoDB.save(recipe.toDBRecipe());

        return recipe;
    }

    public List<Recipe> loadRecipes() {
        List<DBRecipe> dbRecipes = recipeMongoDB.findAll();

        return dbRecipes.stream().map(DBRecipe::toRecipe).collect(Collectors.toList());
    }
}

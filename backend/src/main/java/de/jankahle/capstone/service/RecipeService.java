package de.jankahle.capstone.service;

import de.jankahle.capstone.db.RecipeDB;
import de.jankahle.capstone.model.RecipeDao;
import de.jankahle.capstone.model.Recipe;
import de.jankahle.capstone.model.RecipeDto;
import de.jankahle.capstone.utility.FileUtility;
import de.jankahle.capstone.utility.IdUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeDB recipeDB;
    private final ImageReaderService imageReaderService;
    private final RecipeTextFilterService textFilterService;

    @Autowired
    public RecipeService(RecipeDB recipeDB, ImageReaderService imageReaderService, RecipeTextFilterService textFilterService) {
        this.recipeDB = recipeDB;
        this.imageReaderService = imageReaderService;
        this.textFilterService = textFilterService;
    }

    public Recipe saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeDto.toRecipe();
        if (recipe.getId().isEmpty()) {
            recipe.setId(IdUtility.generateId());
        }
        recipeDB.save(recipe.toDBRecipe());

        return recipe;
    }

    public List<Recipe> loadRecipes() {
        List<RecipeDao> recipeDaos = recipeDB.findAll();

        return recipeDaos.stream().map(RecipeDao::toRecipe).collect(Collectors.toList());
    }

    public Recipe generateRecipe(MultipartFile multipartFile) {

        try {
            File temporaryFile = FileUtility.convertMultipartFileToFile(multipartFile);
            String parsedImage = imageReaderService.getTextFromImage(temporaryFile);
            temporaryFile.delete();

            return textFilterService.parseStringToRecipe(parsedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Recipe.builder().build();
    }
}

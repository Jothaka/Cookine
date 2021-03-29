package de.jankahle.capstone.service;

import de.jankahle.capstone.model.Ingredient;
import de.jankahle.capstone.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.*;

import de.jankahle.capstone.utility.IdUtility;
import org.springframework.stereotype.Service;


@Service
public class RecipeTextFilterService {

    public Recipe parseStringToRecipe(String recipeString) {

        List<Ingredient> ingredients = new ArrayList<>();

        String[] recipeLines = recipeString.split("\n");
        for (String recipeLine : recipeLines) {
            Optional<Ingredient> parsedIngredient = parseIngredient(recipeLine);
            parsedIngredient.ifPresent(ingredient -> ingredient.setId(IdUtility.generateId()));
            parsedIngredient.ifPresent(ingredients::add);
        }

        return Recipe.builder()
                .id(IdUtility.generateId())
                .name("")
                .ingredients(ingredients)
                .build();
    }

    Optional<Ingredient> parseIngredient(String recipeLine) {

        ArrayList<String> matcherResults = filterLineForIngredientDetails(recipeLine);

        if (matcherResults.isEmpty())
            return Optional.empty();

        String matcherResult = normalizeMatcherResults(matcherResults);

        String[] ingredientData = matcherResult.split(" ");

        String name = constructIngredientName(ingredientData);
        String amount = ingredientData[0];
        String measurementUnit = ingredientData.length > 2 ? ingredientData[1] : "";

        Ingredient parsedIngredient = Ingredient.builder()
                .name(name)
                .amount(amount)
                .measurementUnit(measurementUnit)
                .build();

        return Optional.of(parsedIngredient);
    }

    /*
        <Filter Description>
        \\d+ we expect an listing of an ingredient starts with a number/the amount of the ingredient this can have multiple digits.
        [\/.,]?\\d? the amount may be partial so we need to consider things like 1/2 or 1.5, this is optional.
        \\s? depending on the recipe there might be a space between the amount and the measurementUnit or not ("1 kg" or "1kg")
        [a-zA-Z]* if a measurementUnit is provided it will always be in letters, this is optional as sometimes the measurementUnit is in the ingredients name ("Knoblauchzehe")
        \\s[A-Z][a-z]+ the ingredient name is expected to start with a capital letter as german nouns always start with one.
        ([,.]\s[a-zA-Z ]+)? if there is additional information for the ingredient it is usually seperated with ',' or '.' (", mehligkochend") this will be later added to the ingredientname.
        </Filter Description>
    */
    private ArrayList<String> filterLineForIngredientDetails(String recipeLine) {
        String filter = "(\\d+[\\/.,-]?\\d?\\s?[a-zA-Z]*\\s[A-Z][a-z]+([,.(]\\s[a-zA-Z ()]+)?)";
        Pattern pattern = Pattern.compile(filter);
        Matcher matcher = pattern.matcher(recipeLine);
        ArrayList<String> matcherResults = new ArrayList<>();

        while (matcher.find()) {
            matcherResults.add(matcher.group());
        }

        //there should only be one ingredient per line
        if (matcherResults.size() > 1)
            matcherResults.clear();

        return matcherResults;
    }

    private void insertMissingSplitSeperators(StringBuilder matcherResult) {
        for (int i = 0; i < matcherResult.length() - 1; i++) {
            if (Character.isDigit(matcherResult.charAt(i))) {
                if (Character.isAlphabetic(matcherResult.charAt(i + 1))) {
                    matcherResult.insert(i + 1, ' ');
                }
            }
        }
    }

    private String normalizeMatcherResults(ArrayList<String> matcherResults) {
        StringBuilder matcherResult = new StringBuilder(matcherResults.get(0));
        insertMissingSplitSeperators(matcherResult);
        return matcherResult.toString();
    }

    private String constructIngredientName(String[] ingredientData) {
        StringBuilder nameBuilder = new StringBuilder(ingredientData[ingredientData.length - 1]);
        if (ingredientData.length > 3) {
            nameBuilder = new StringBuilder(ingredientData[2]);
            for (int i = 3; i < ingredientData.length; i++) {
                nameBuilder.append(" ");
                nameBuilder.append(ingredientData[i]);
            }
        }
        return nameBuilder.toString();
    }
}
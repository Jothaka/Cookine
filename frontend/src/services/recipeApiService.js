import axios from "axios";

const recipeUrl = "/api/recipe";

export const getAllRecipes = () =>
    axios.get(recipeUrl).then((response) => response.data);

export const saveRecipe = (recipe) =>
    axios.post(recipeUrl, {
        "id": recipe.id,
        "name": recipe.name,
        "ingredients": recipe.ingredients
    }).then((response) => response.data)

export const deleteRecipe = (recipe) =>
    axios.delete(`${recipeUrl}/${recipe.id}`);
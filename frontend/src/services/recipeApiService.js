import axios from "axios";

const recipeUrl = "/api/recipe";

export  const getAllRecipes = () =>
    axios.get(recipeUrl).then((response)=> response.data);
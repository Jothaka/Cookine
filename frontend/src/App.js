import FileUpload from "./components/FileUpload";
import RecipeList from "./components/RecipeList";
import {useEffect, useState} from "react";
import {getAllRecipes} from "./services/recipeApiService";

function App() {
    const [recipes, setRecipes] = useState([]);

    useEffect(() => {
            getAllRecipes()
                .then(setRecipes)
                .catch((error) => console.error(error))
        },
        [])


    return (
        <div>
            <RecipeList recipes={recipes} />
            <FileUpload/>
        </div>
    );
}

export default App;
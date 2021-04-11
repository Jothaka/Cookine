import {useEffect, useState} from "react";
import {deleteRecipe, getAllRecipes} from "./services/recipeApiService";
import {Route, Switch} from 'react-router-dom';
import RecipeDraft from "./components/RecipeDraft";
import useDraftHooks from "./hooks/DraftHooks";
import MainPage from "./pages/MainPage";

function App() {
    const [recipes, setRecipes] = useState([]);
    const [onDraftReceived,
        onDraftNameUpdated,
        onDraftIngredientsUpdated,
        saveDraft, draft] = useDraftHooks(setRecipes, recipes);

    const onDeleteRecipe = (recipeToDelete) => {
        deleteRecipe(recipeToDelete).then(() => {
            const updatedRecipes = recipes.filter((recipe)=> recipe.id !== recipeToDelete.id)
            setRecipes(updatedRecipes);
        })
    }

    useEffect(() => {
            getAllRecipes()
                .then(setRecipes)
                .catch((error) => console.error(error))
        },
        [])

    return (
            <Switch>
                <Route exact path="/">
                    <MainPage onDraftReceived={onDraftReceived} recipes={recipes} onDelete={onDeleteRecipe}/>
                </Route>
                <Route path="/draft">
                    {draft && <RecipeDraft
                        recipe={draft}
                        onRecipeNameUpdated={onDraftNameUpdated}
                        onIngredientsUpdate={onDraftIngredientsUpdated}
                        saveDraft={saveDraft}/>}
                </Route>
            </Switch>
    );
}

export default App;
import FileUpload from "./components/FileUpload";
import RecipeList from "./components/RecipeList";
import {useEffect, useState} from "react";
import {getAllRecipes} from "./services/recipeApiService";
import {Route, Switch} from 'react-router-dom';
import RecipeDraft from "./components/RecipeDraft";

function App() {
    const [recipes, setRecipes] = useState([]);
    const [draft, setDraft] = useState('');

    useEffect(() => {
            getAllRecipes()
                .then(setRecipes)
                .catch((error) => console.error(error))
        },
        [])

    const onDraftUpdated = (updatedDraft) => {
        setDraft(updatedDraft);
    }

    return (
        <div>
            <Switch>
                <Route exact path="/">
                    <RecipeList recipes={recipes}/>
                    <FileUpload onDraftReceived={onDraftUpdated}/>
                </Route>
                <Route path="/draft">
                    { draft !== '' && <RecipeDraft recipe={draft} onRecipeUpdated={onDraftUpdated} /> }
                </Route>
            </Switch>
        </div>
    );
}

export default App;
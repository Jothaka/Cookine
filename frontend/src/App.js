import FileUpload from "./components/FileUpload";
import RecipeList from "./components/RecipeList";
import {useEffect, useState} from "react";
import {getAllRecipes} from "./services/recipeApiService";
import {Route, Switch} from 'react-router-dom';
import RecipeDraft from "./components/RecipeDraft";
import useDraftHooks from "./hooks/DraftHooks";
import styled from "styled-components/macro";

function App() {
    const [recipes, setRecipes] = useState([]);
    const [onDraftReceived,
        onDraftNameUpdated,
        onDraftIngredientsUpdated,
        saveDraft, draft] = useDraftHooks(setRecipes, recipes);

    useEffect(() => {
            getAllRecipes()
                .then(setRecipes)
                .catch((error) => console.error(error))
        },
        [])

    return (
            <Switch>
                <Route exact path="/">
                    <Wrapper>
                        <RecipeList recipes={recipes}/>
                        <FileUpload onDraftReceived={onDraftReceived}/>
                    </Wrapper>
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

const Wrapper = styled.div`
  display: grid;
  grid-template-rows: 1fr auto;
`

export default App;
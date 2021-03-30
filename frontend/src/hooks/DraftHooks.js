import {useState} from "react";
import {useHistory} from "react-router-dom";
import {saveRecipe} from "../services/recipeApiService";

export default function useDraftHooks(setRecipes, recipes) {
    const [draft, setDraft] = useState('');
    const history = useHistory();

    const onDraftReceived = (response) => {
        setDraft(response)
    }

    const onDraftNameUpdated = (updatedDraftNameEvent) => {
        setDraft(draft => ({
            ...draft,
            name: updatedDraftNameEvent.target.value,
        }));
    }

    const onDraftIngredientsUpdated = (ingredients) => {
        setDraft(draft => ({
            ...draft,
            ingredients: ingredients,
        }))
    }

    const saveDraft = (submitEvent) => {
        submitEvent.preventDefault()
        saveRecipe(draft).then((response) => {
                setRecipes([...recipes, response])
                history.push("/")
            }
        )
    }

    return [onDraftReceived, onDraftNameUpdated, onDraftIngredientsUpdated, saveDraft, draft];
}
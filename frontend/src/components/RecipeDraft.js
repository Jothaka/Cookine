import {makeStyles, TextField} from "@material-ui/core";
import {useState} from "react";
import IngredientEditor from "./IngredientEditor";

export default function RecipeDraft({recipe, onRecipeUpdated}) {
    const [ingredients, setIngredients] = useState(recipe.ingredients);
    const classes = useStyles();

    const onNameChanged = (changeEvent) => {
        recipe.name = changeEvent.value;
        onRecipeUpdated(recipe);
    }

    const onIngredientUpdated = (updatedIngredient) => {
        setIngredients(ingredients.map(ingredient =>
            ingredient.id === updatedIngredient.id ? updatedIngredient : ingredient))
        recipe.ingredients = ingredients
        onRecipeUpdated(recipe)
    }

    return (
        <>
            <form className={classes.root}>
                <TextField
                    required id="recipename"
                    label="Rezeptname"
                    value={recipe.name}
                    onChange={onNameChanged}
                />
                {ingredients.map(ingredient => (
                    <IngredientEditor key={ingredient.id} ingredient={ingredient} onChange={onIngredientUpdated}/>
                ))}
            </form>
        </>
    )
}

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
        },
    }
}));
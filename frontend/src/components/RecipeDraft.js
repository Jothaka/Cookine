import {Button, makeStyles, TextField} from "@material-ui/core";
import {useState} from "react";
import IngredientEditor from "./IngredientEditor";

export default function RecipeDraft({recipe, onRecipeNameUpdated, onIngredientsUpdated,  saveDraft}) {
    const [ingredients, setIngredients] = useState(recipe.ingredients);

    const classes = useStyles();

    const onIngredientUpdated = (updatedIngredient) => {
        setIngredients(ingredients.map(ingredient =>
            ingredient.id === updatedIngredient.id ? updatedIngredient : ingredient))
        onIngredientsUpdated(ingredients)
    }

    return (
        <>
            <form className={classes.root} onSubmit={saveDraft}>
                <TextField
                    required id="recipename"
                    label="Rezeptname"
                    value={recipe.name}
                    onChange={onRecipeNameUpdated}
                />
                {ingredients.map(ingredient => (
                    <IngredientEditor key={ingredient.id} ingredient={ingredient} onChange={onIngredientUpdated}/>
                ))}
                <Button type="submit" >Speichern</Button>
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
import {Button, IconButton, makeStyles, TextField} from "@material-ui/core";
import {useState} from "react";
import IngredientEditor from "./IngredientEditor";
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import {CheckOutlined} from "@material-ui/icons";
import styled from "styled-components/macro";

export default function RecipeDraft({recipe, onRecipeNameUpdated, onIngredientsUpdate, saveDraft}) {
    const [ingredients, setIngredients] = useState(recipe.ingredients);

    const classes = useStyles();

    const onIngredientUpdated = (updatedIngredient) => {
        setIngredients(ingredients.map(ingredient =>
            ingredient.id === updatedIngredient.id ? updatedIngredient : ingredient))
        onIngredientsUpdate(ingredients.map(ingredient =>
            ingredient.id === updatedIngredient.id ? updatedIngredient : ingredient))
    }

    const onAddIngredient = () => {
        setIngredients(
            [...ingredients, {
                "id": `${uuid()}`,
                "name": "",
                "measurementUnit": "",
                "amount": ""
            }]
        )
        onIngredientsUpdate(
            [...ingredients, {
            "id": `${uuid()}`,
            "name": "",
            "measurementUnit": "",
            "amount": ""
        }])
    }

    const onDeleteIngredient = (ingredientId) => {
        setIngredients(
            ingredients.filter(ingredient =>
                ingredient.id !== ingredientId
            )
        )
        onIngredientsUpdate(
            ingredients.filter(ingredient =>
                ingredient.id !== ingredientId
            )
        )
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
                    <IngredientEditor
                        key={ingredient.id}
                        ingredient={ingredient}
                        onChange={onIngredientUpdated}
                        onDelete={onDeleteIngredient}
                    />
                ))}
                <ButtonWrapper>
                    <IconButton type="button" onClick={onAddIngredient}>
                        <AddCircleOutlineOutlinedIcon/>
                    </IconButton>
                    <Button type="submit"><CheckOutlined/></Button>
                </ButtonWrapper>
            </form>
        </>
    )
}

function uuid() {
    //src: https://gist.github.com/jed/982883
    // eslint-disable-next-line
    return (([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c => (c ^ (window.crypto || window.msCrypto).getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)));
}

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
        },
    }
}));

const ButtonWrapper = styled.div`
  align-content: space-between;
`
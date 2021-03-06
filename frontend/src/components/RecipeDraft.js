import {Button, createMuiTheme, IconButton, makeStyles, MuiThemeProvider, TextField} from "@material-ui/core";
import {useState} from "react";
import IngredientEditor from "./IngredientEditor";
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import {CheckOutlined} from "@material-ui/icons";

const theme = createMuiTheme({
    palette: {
        secondary: {
            main: "rgba(var(--borderColor))"
        }
    }
})

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
        },
        '& .MuiInputBase-root': {
            color: "var(--backgroundColorForms)",
        },
        '& .MuiFormLabel-root': {
            color: "var(--borderColor)",
        }
    },
    btnLeft: {
        float: 'left',
        color: "var(--backgroundColorForms)"
    },
    btnRight: {
        float: 'right',
        color: "var(--backgroundColorForms)"
    }
}));

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
        const id = uuid();
        setIngredients(
            [...ingredients, {
                "id": `${id}`,
                "name": "",
                "measurementUnit": "",
                "amount": ""
            }]
        )
        onIngredientsUpdate(
            [...ingredients, {
                "id": `${id}`,
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
        <MuiThemeProvider theme={theme}>
            <form className={classes.root} onSubmit={saveDraft}>
                <TextField
                    required id="recipename"
                    label="Rezeptname"
                    color="secondary"
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
                <IconButton className={classes.btnLeft} type="button" onClick={onAddIngredient}>
                    <AddCircleOutlineOutlinedIcon/>
                </IconButton>
                <Button className={classes.btnRight} type="submit">
                    <CheckOutlined/>
                </Button>
            </form>
        </MuiThemeProvider>
    )
}

function uuid() {
    //src: https://gist.github.com/jed/982883
    // eslint-disable-next-line
    return (([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c => (c ^ (window.crypto || window.msCrypto).getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)));
}
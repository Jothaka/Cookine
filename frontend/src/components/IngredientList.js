import Ingredient from "./Ingredient";

export default function IngredientList({ingredients}) {
    return (
        <>
            {ingredients.map(ingredient => <Ingredient key={ingredient.id} ingredient={ingredient}/>)}
        </>
    )
}
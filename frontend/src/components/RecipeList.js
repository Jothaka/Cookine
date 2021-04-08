import styled from "styled-components/macro";
import Recipe from "./Recipe";

export default function RecipeList({recipes}) {
    return (
        <List>
            {recipes.map((recipe)=>(
                <Recipe recipe={recipe} key={recipe.id} />
            ))}
        </List>
    )
}

const List = styled.ul`
  list-style: none;
  overflow-y: scroll;
  padding: 2px;
  margin: 1px;
`
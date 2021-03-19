import styled from "styled-components/macro";
import Recipe from "./Recipe";

export default function RecipeList({recipes}) {
    return (
        <List>
            {recipes.map((recipe)=>(
                <Recipe recipe={recipe} />
            ))}
        </List>
    )
}

const List = styled.ul`
  list-style: none;
  padding: 2px;
  margin: 1px;
`
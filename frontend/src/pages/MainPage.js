import styled from "styled-components/macro";
import RecipeList from "../components/RecipeList";
import FileUpload from "../components/FileUpload";

export default  function MainPage({recipes, onDraftReceived}) {
    return(
        <Wrapper>
            <RecipeList recipes={recipes}/>
            <FileUpload onDraftReceived={onDraftReceived}/>
        </Wrapper>
    )
}

const Wrapper = styled.div`
  display: grid;
  grid-template-rows: 1fr auto;
`
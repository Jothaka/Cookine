import styled from "styled-components/macro";
import {Card, CardActionArea, CardContent, Typography} from "@material-ui/core";
import {useState} from "react";
import IngredientList from "./IngredientList";

export default function Recipe({recipe}) {
    const [showDetails, setShowDetails] = useState(false)

    return (
        <ListItem>
            <Card style={showDetails ? cardStyleSelected : cardStyle}>
                <CardContent>
                    <CardActionArea onClick={() => setShowDetails(!showDetails)}>
                        <CardContentOrder>
                            <Typography>{recipe.name}</Typography>
                        </CardContentOrder>
                    </CardActionArea>
                </CardContent>
            </Card>
            {showDetails && <IngredientList ingredients={recipe.ingredients}/>}
        </ListItem>
    )
}

const ListItem = styled.li`
  margin: 3px;
`
const CardContentOrder = styled.div`
  display: flex;
  justify-content: space-between;
`

const cardStyle = {
    backgroundColor: "var(--backgroundColorSecondary)",
    borderColor: "var(--borderColor)",
    borderStyle: "solid",
    color: "var(--backgroundColorForms)",
    borderRadius: "20px"
}

const cardStyleSelected = {
    backgroundColor: "var(--backgroundColorSecondary)",
    borderColor: "var(--borderColor)",
    borderStyle: "solid",
    color: "var(--backgroundColorForms)",
    borderRadius: "20px"
}
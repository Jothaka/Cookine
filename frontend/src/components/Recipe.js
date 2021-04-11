import styled from "styled-components/macro";
import {Card, CardActionArea, CardContent, IconButton, Typography} from "@material-ui/core";
import {useState} from "react";
import IngredientList from "./IngredientList";
import {CancelOutlined} from "@material-ui/icons";

export default function Recipe({recipe, onDelete}) {
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
            {showDetails &&
            <div>
                <IngredientList ingredients={recipe.ingredients}/>
                <IconButton type="button"
                            style={{color: "var(--borderColor)"}}
                            onClick={() => onDelete(recipe)}>
                    <CancelOutlined/>
                </IconButton>
            </div>
            }
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
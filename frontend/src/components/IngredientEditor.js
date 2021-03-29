import {makeStyles, TextField} from "@material-ui/core";
import {useState} from "react";
import styled from "styled-components/macro";

export default function IngredientEditor({ingredient}) {
    const [ingredientData, setIngredientData] = useState(ingredient)
    const classes = useStyles();


    const onNameChange = nameChangedEvent => {
        setIngredientData(ingredientData => ({
                ...ingredientData,
                [nameChangedEvent.target.id]: nameChangedEvent.target.value,
            })
        )
    }

    return (
        <IngredientData key={ingredientData.id}>
            <TextField
                label="Menge"
                id="amount"
                value={ingredientData.amount}
                onChange={onNameChange}
                inputProps={{'aria-label': 'Menge'}}
                className={classes.unit}
            />
            <TextField
                label='Einheit'
                id="measurementUnit"
                value={ingredientData.measurementUnit}
                onChange={onNameChange}
                inputProps={{'aria-label': 'Einheit'}}
                className={classes.unit}
            />
            <TextField
                label="Name"
                id="name"
                value={ingredientData.name}
                onChange={onNameChange}
                inputProps={{'aria-label': 'Name',}}
                className={classes.title}
            />
        </IngredientData>
    )
}

const IngredientData = styled.div`
  display: flex;
`

const useStyles = makeStyles((theme) => ({
    unit: {
        width: '15%'
    },
    title: {
        width: '70%'
    }
}));
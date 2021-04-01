import {IconButton, makeStyles, TextField} from "@material-ui/core";
import {useState} from "react";
import styled from "styled-components/macro";
import {CancelOutlined} from "@material-ui/icons";

export default function IngredientEditor({ingredient, onChange, onDelete}) {
    const [ingredientData, setIngredientData] = useState(ingredient)
    const classes = useStyles();

    const onDataChanged = dataChangedEvent => {
        setIngredientData(ingredientData => ({
                ...ingredientData,
                [dataChangedEvent.target.id]: dataChangedEvent.target.value,
            })
        )
        onChange({
            ...ingredientData,
            [dataChangedEvent.target.id]: dataChangedEvent.target.value,
        });
    }

    return (
        <IngredientData key={ingredientData.id}>
            <IconButton type="button"
                        className={classes.cancel}
                        onClick={() => {onDelete(ingredientData.id)}}>
                <CancelOutlined/>
            </IconButton>
            <TextField
                label="Menge"
                id="amount"
                value={ingredientData.amount}
                onChange={onDataChanged}
                inputProps={{'aria-label': 'Menge'}}
                className={classes.amount}
            />
            <TextField
                label='Einheit'
                id="measurementUnit"
                value={ingredientData.measurementUnit}
                onChange={onDataChanged}
                inputProps={{'aria-label': 'Einheit'}}
                className={classes.unit}
            />
            <TextField
                label="Name"
                id="name"
                value={ingredientData.name}
                onChange={onDataChanged}
                inputProps={{'aria-label': 'Name',}}
                className={classes.title}
            />
        </IngredientData>
    )
}

const IngredientData = styled.div`
  display: flex;
`

const useStyles = makeStyles(() => ({
    cancel: {
        width: "auto"
    },
    amount: {
        width: '10%'
    },
    unit: {
        width: '17%'
    },
    title: {
        width: '65%'
    }
}));
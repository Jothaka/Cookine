import {List, ListItem, ListItemText, makeStyles} from "@material-ui/core";

const useStyles = makeStyles(() => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: "var(--borderColor)",
        display: "flex",
        padding: "0",
        borderRadius: "10px"
    },
    unit: {
        width: '19%',
        paddingTop:"0",
        paddingBottom:"0"
    },
    amount: {
        width: '16%',
        paddingTop:"0",
        paddingBottom:"0"
    },
    title: {
        width:'65%',
        paddingTop:"0",
        paddingBottom:"0"
    }
}));

export default function Ingredient({ingredient}) {
    const classes = useStyles();

    return (
        <List className={classes.root}>
            <ListItem className={classes.amount}>
                <ListItemText primary={ingredient.amount} secondary="Menge" />
            </ListItem>
            <ListItem className={classes.unit}>
                <ListItemText primary={ingredient.measurementUnit} secondary={ingredient.measurementUnit ? "Einheit" : ""} />
            </ListItem>
            <ListItem className={classes.title}>
                <ListItemText primary={ingredient.name} secondary="Name" />
            </ListItem>
        </List>
    )
}
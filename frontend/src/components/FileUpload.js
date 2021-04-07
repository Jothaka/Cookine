import {fileUpload} from "../services/fileUploadService";
import {useHistory} from "react-router-dom";
import {IconButton, makeStyles} from "@material-ui/core";
import AddCircleOutlineOutlinedIcon from "@material-ui/icons/AddCircleOutlineOutlined";

const useStyles = makeStyles((theme) => ({
    btnChoose: {
        color: "var(--backgroundColorForms)",
        boxShadow: "2px 2px blur black",
        bottom: theme.spacing(2),
        right: theme.spacing(2),
        position: "fixed"
    }
}))

export default function FileUpload({onDraftReceived}) {

    const history = useHistory();
    const classes = useStyles();

    const onChange = (changeEvent) => {
        fileUpload(changeEvent.target.files[0]).then((response) => {
            onDraftReceived(response);
            history.push("/draft");
        })
    }

    return (
        <div>
            <label htmlFor="btn-upload">
                <input
                    id="btn-upload"
                    name="btn-upload"
                    style={{display: "none"}}
                    type="file"
                    accept="image/*"
                    onChange={onChange}/>
                <IconButton
                    className={classes.btnChoose}
                    variant="outlined"
                    component="span"
                >
                    <AddCircleOutlineOutlinedIcon style={{fontSize: 50}}/>
                </IconButton>
            </label>
        </div>
    );
}
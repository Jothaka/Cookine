import {fileUpload} from "../services/fileUploadService";
import {useHistory} from "react-router-dom";
import {IconButton, makeStyles} from "@material-ui/core";
import AddCircleOutlineOutlinedIcon from "@material-ui/icons/AddCircleOutlineOutlined";

const useStyles = makeStyles((theme) => ({
    btnChoose: {
        color: "var(--backgroundColorForms)",
        bottom: theme.spacing(2),
        right: theme.spacing(2),
        position: "fixed"
    }
}))

export default function FileUpload({onDraftReceived}) {

    const history = useHistory();
    const classes = useStyles();

    const uploadFile = (changeEvent) => {
        fileUpload(changeEvent.target.files[0])
            .then((recipeDraft) => {
                onDraftReceived(recipeDraft);
                history.push("/draft");
            })
            .catch((error) => console.error(error))
    }

    return (
        <div>
            <label>
                <input
                    id="btn-upload"
                    name="btn-upload"
                    style={{display: "none"}}
                    type="file"
                    accept="image/*"
                    onChange={uploadFile}/>
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
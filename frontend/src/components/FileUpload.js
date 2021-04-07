import {fileUpload} from "../services/fileUploadService";
import {useState} from "react";
import {useHistory} from "react-router-dom";

export default function FileUpload({onDraftReceived}) {

    const [file, setFile] = useState(undefined);
    const history =  useHistory();

    const onFormSubmit = (formEvent) => {
        formEvent.preventDefault();
        fileUpload(file).then((response) => {
            onDraftReceived(response);
            history.push("/draft");
        })
    }

    const onChange = (changeEvent) => {
        setFile(changeEvent.target.files[0]);
    }

    return (
        <form onSubmit={onFormSubmit}>
            <h3>File Upload</h3>
            <input type="file" name="file" onChange={onChange} accept="image/*"/>
            <button type="submit">Upload</button>
        </form>
    );
}
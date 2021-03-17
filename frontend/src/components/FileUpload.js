import {fileUpload} from "../services/fileUploadService";
import {useState} from "react";

export default function FileUpload() {

    const [file, setFile] = useState(undefined);

    const onFormSubmit = (formEvent) => {
        formEvent.preventDefault();
        fileUpload(file).then((response) => {
            console.log(response.data)
        })
    }

    const onChange = (changeEvent) => {
        setFile(changeEvent.target.files[0]);
    }

    return (
        <form onSubmit={onFormSubmit}>
            <h1>File Upload</h1>
            <input type="file" name="file" onChange={onChange} accept="image/*"/>
            <button type="submit">Upload</button>
        </form>
    );
}
import axios from 'axios'

export const fileUpload = (file) => {
    const formData = new FormData();
    formData.append('file', file);
    const config = {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }
    return axios.post("/api/upload", formData, config);
}
import axios from 'axios'

export const fileUpload = (file) => {
    const formData = new FormData();
    formData.append('file', file);

    return axios.post("/api/upload", formData).then((response) => response.data);
}
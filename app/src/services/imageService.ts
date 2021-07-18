import { api } from './api';

type UploadProps = {
    data: FormData;
}

export const fetchUpload = async (data: UploadProps) => {

    const url = `${api.defaults.baseURL}/storage/upload`;
    const response = await fetch(url, { method: "POST", body: data.data })

    const jsonResult = await response.json();
    return jsonResult;
}
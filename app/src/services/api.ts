import axios from 'axios';

const api = axios.create({
    baseURL: 'http://192.168.0.11:8080'
});

export { api }
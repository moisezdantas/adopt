import { Base64 } from '../utils/boa';
import axios, { Method } from 'axios';
import { CLIENT_ID, CLIENT_SECRET } from './auth-api';
import { api } from './api';
import { stringify } from 'query-string';
import { Person } from '../interfaces/person';

type RequestParams = {
    method?: Method;
    url: string;
    data?: object | string;
    params?: object;
    headers?: object;
}

type LoginData = {
    username: string;
    password: string;
}

const BASE_URL = api.defaults.baseURL

export const MakeRequest = ({ method = 'GET', url, data, params, headers }: RequestParams) => {

    return axios({
        method,
        url: `${BASE_URL}${url}`,
        data,
        params,
        headers
    });
}

export const MakeLogin = (loginData: LoginData) => {
    const token = `${CLIENT_ID}:${CLIENT_SECRET}`;

    const headers = {
        'Authorization': `Basic ${Base64.btoa(token)}`,
        'Content-Type': 'application/x-www-form-urlencoded',
    }

    return MakeRequest({
        url: '/oauth/token',
        data: stringify({ ...loginData, grant_type: "password" }),
        method: 'POST',
        headers
    })
}

export const CreatePerson = (data: Person) => {
    return MakeRequest({
        url: '/person',
        data: { ...data },
        method: 'POST',
    })
}


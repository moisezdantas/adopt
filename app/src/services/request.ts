import { Animal } from './../interfaces/animal';
import { Base64 } from '../utils/boa';
import axios, { Method } from 'axios';
import { CLIENT_ID, CLIENT_SECRET } from './auth-api';
import { api } from './api';
import { stringify } from 'query-string';
import { Person } from '../interfaces/person';

import { AnimalType } from '../interfaces/animalType';
import { http } from './http'

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

export type AnimalPagePaginationProps = {
    content: Animal[];
    totalPages: number;
    totalElements: number;
}

type PaginationRequest = {
    page?: number;
    linesPerPage?: number;
    direction?: string;
    orderBy?: string;
}

type AnimalPaginationRequest = PaginationRequest & {
    animalTypeId?: number;
}

type AdoptAnimaProps = {
    animalId: number;
    userId: number;
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

    const headers = { 'Content-Type': 'application/json' }
    return MakeRequest({
        url: '/person',
        data: { ...data },
        method: 'POST',
    })
}

export const fetchAnimalType = async (): Promise<AnimalType[]> => {
    const { data } = await http.get<AnimalType[]>('/animal-type');
    return data;
};

export const fetchAnimal = async ({
    animalTypeId,
    page = 0,
    linesPerPage = 12,
    direction = 'ASC',
    orderBy = 'name'
}: AnimalPaginationRequest): Promise<AnimalPagePaginationProps> => {

    const params = animalTypeId ? `/animal?animalTypeId=${animalTypeId}&` : '/animal?';
    const url = `${params}page=${page}&linesPerPage=${linesPerPage}&direction=${direction}&orderBy=${orderBy}`
    const { data } = await http.get<AnimalPagePaginationProps>(url);
    return data;
};


export const adoptAnimal = async ({
    animalId, userId
}: AdoptAnimaProps): Promise<void> => {

    await http.post<AdoptAnimaProps>('/adopter', {animalId, userId});
};

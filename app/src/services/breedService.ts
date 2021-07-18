import { Breed } from './../interfaces/breed';
import { http } from './http'

export const fetchBreeds = async (): Promise<Breed[]> => {
    const { data } = await http.get<Breed[]>('/breed');
    return data;
};

import { AnimalType } from './../interfaces/animalType';
import { http } from './http'


export const fetchAnimalType = async (): Promise<AnimalType[]> => {
    const { data } = await http.get<AnimalType[]>('/animal-type');
    return data;
};

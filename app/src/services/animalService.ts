import { Animal } from './../interfaces/animal';

import { http } from './http'

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
    animalId: string;
    userId: string;
}

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

export const createAdoptAnimal = async ({
    animalId, userId
}: AdoptAnimaProps): Promise<void> => {

    await http.post<AdoptAnimaProps>('/adopter', { animalId, userId });
};


export const createAnimal = async (data: Animal) : Promise<Animal> => {

    const response = await http.post<Animal>('animal', data);

    return response.data;
}
import { http } from './http';
import { Address } from './../interfaces/address';
import { User } from './../hook/auth';

export interface PersonProps {
    mobilePhone: string;
    user: User
    address?: Address[]
}

export const updatePerson = async (data: PersonProps): Promise<void> => {
    await http.post<PersonProps>('/person/profile', {...data});
};

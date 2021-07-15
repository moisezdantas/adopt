import { Address } from "./address";
import { Breed } from "./breed";

export interface Animal {
    id: string,
    name: string,
    rga: string | null,
    birthDate: string,
    deficiency: string | null,
    typeVaccinated: string | null,
    typeAnimalGender: string | number,
    year: string | null,
    note: string,
    breed: Breed
    address: Address;
    imageUrl: string;
}
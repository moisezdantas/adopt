import { User } from "./user";

export interface Person {
    typeGender: number | string;
    mobilePhone: string;
    name: string;
    user: User
}
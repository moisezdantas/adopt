import { Role } from "./roles";

export interface User {
    email: string,
    password: string,
    roles: Role[]
}
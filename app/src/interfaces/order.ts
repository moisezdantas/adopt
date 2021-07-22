import { User } from './../hook/auth';

export interface Order {
    id: string,
    name: string,
    sku?: string | null,
    description?: string,
    price: string | null,
    payerID?: string | null,
    paymentId: string | number,
    user: User
}
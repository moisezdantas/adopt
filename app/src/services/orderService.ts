import { Order } from './../interfaces/order';
import { AxiosResponse } from 'axios';

import { http } from './http'

export const createOrder = async (data: Omit<Order, "id">) : Promise<Order> => {

    const response = await http.post<Omit<Order, "id">, AxiosResponse<Order>>('ordem', data);

    return response.data;
}
import { BasketItem } from "./basket-item";
import { Client } from "./client";

export interface Order {
  id: number;
  idClient: number;
  state: OrderState;
  client?: Client; 
  items: BasketItem[];
}

export enum OrderState {
  CANCELED = 'CANCELED',
  OPTION = 'OPTION',
  CONFIRMED = 'CONFIRMED'
}


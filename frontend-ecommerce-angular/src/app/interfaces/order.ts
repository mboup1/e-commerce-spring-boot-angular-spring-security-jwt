import { BasketItem } from "./basket-item";
import { Client } from "./client";

export interface Order {
  id: number;
  idClient: number;
  state: OrderState;
  client: Client;
  date: string; // Ajout de la propriété de date
  basketItems: BasketItem[];
}

export enum OrderState {
  CANCELED = 'CANCELED',
  OPTION = 'OPTION',
  CONFIRMED = 'CONFIRMED'
}


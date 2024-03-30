import { BasketItem } from "./basket-item";

export interface Basket {
  id: number;
  nameBasket: string;
  items: BasketItem[];
}

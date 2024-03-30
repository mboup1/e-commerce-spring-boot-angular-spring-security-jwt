import { Product } from './product';
import { Order } from './order';
import { Basket } from './basket';

export interface BasketItem {
  id: number;
  basket?: Basket;
  product: Product;
  quantity: number;
  order?: Order;
}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Basket } from '../../interfaces/basket';
import { API_BASE_URL } from '../../config/config';
import { BasketItem } from '../../interfaces/basket-item';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BasketService {

  constructor(private http: HttpClient) { }

  getAllBaskets(): Observable<Basket[]> {
    return this.http.get<Basket[]>(`${API_BASE_URL}/baskets`);
  }

  addBasket(basket: Basket): Observable<Basket> {
    return this.http.post<Basket>(`${API_BASE_URL}/baskets`, basket);
  }

  deleteBasket(id: number): Observable<Basket> {
    return this.http.delete<Basket>(`${API_BASE_URL}/baskets/${id}`);
  }

  getBasketById(id: number): Observable<Basket> {
    return this.http.get<Basket>(`${API_BASE_URL}/basket/${id}`);
  }

  // getBasketById(id: number): Observable<Basket> {
  //   let jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW1lIiwicm9sZXMiOlsiQURNSU4iLCJVU0VSIl0sImV4cCI6MTcxOTU4OTQ0M30.162G_IqEXMsgC6rQyF6JPu46zoOHkuwbj1eyxpC8C3Q";
  //   // let jwt = this.authService.getToken();
  //   jwt = "Bearer " + jwt;
  //   let httpHeaders = new HttpHeaders({ "Authorization": jwt })

  //   return this.http.get<Basket>(`${API_BASE_URL}/basket/${id}`, { headers: httpHeaders });
  // }



  updateBasket(basket: Basket): Observable<Basket> {
    return this.http.put<Basket>(`${API_BASE_URL}/baskets/${basket.id}`, basket);
  }


  getTotalPrice(): Observable<number> {
    return this.http.get<number>(`${API_BASE_URL}/basket/1/totalPrice`);
  }

  addItemToBasket(basketItem: BasketItem): Observable<BasketItem> {
    return this.http.post<BasketItem>(`${API_BASE_URL}/basket-items/1/items`, basketItem);
  }

  removeItemFromBasket(basketItem: BasketItem): Observable<void> {
    return this.http.delete<void>(`${API_BASE_URL}/basket-items/1/items`, { body: basketItem });
  }


}

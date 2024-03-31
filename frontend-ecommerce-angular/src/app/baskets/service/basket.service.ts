import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Basket } from '../../interfaces/basket';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';

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
    return this.http.get<Basket>(`${API_BASE_URL}/baskets/${id}`);
  }

  updateBasket(basket: Basket): Observable<Basket> {
    return this.http.put<Basket>(`${API_BASE_URL}/baskets/${basket.id}`, basket);
  }
  

  getTotalPrice(): Observable<number> {
    return this.http.get<number>(`${API_BASE_URL}/basket/1/totalPrice`);
  }
}

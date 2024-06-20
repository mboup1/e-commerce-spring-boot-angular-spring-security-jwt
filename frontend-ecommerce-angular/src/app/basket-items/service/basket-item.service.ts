import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BasketItem } from '../../interfaces/basket-item';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class BasketItemService {

  constructor(private http: HttpClient) { }

  getAllBasketItems(): Observable<BasketItem[]> {
    return this.http.get<BasketItem[]>(`${API_BASE_URL}/basket-items`);
  }
}

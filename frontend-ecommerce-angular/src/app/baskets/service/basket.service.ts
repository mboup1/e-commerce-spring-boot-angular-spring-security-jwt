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

  getTotalPrice(): Observable<number> {
    return this.http.get<number>(`${API_BASE_URL}/basket/1/totalPrice`);
  }
}

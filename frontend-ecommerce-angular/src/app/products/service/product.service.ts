import { Injectable } from '@angular/core';
import { Product } from '../../interfaces/product';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${API_BASE_URL}/products`);
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${API_BASE_URL}/products`, product);
  }

  deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${API_BASE_URL}/products/${id}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${API_BASE_URL}/products/${id}`);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${API_BASE_URL}/products/${product.idProd}`, product);
  }
}

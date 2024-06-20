import { Injectable } from '@angular/core';
import { Product } from '../../interfaces/product';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private authService: AuthService,) { }

  // getAllProducts(): Observable<Product[]> {
  //   return this.http.get<Product[]>(`${API_BASE_URL}/products`);
  // }

  getAllProducts(): Observable<Product[]> {
    let jwt = this.authService.getToken();
    jwt = "Bearer " + jwt;
    let httpHeaders = new HttpHeaders({ "Authorization": jwt })

    return this.http.get<Product[]>(`${API_BASE_URL}/products`, { headers: httpHeaders });
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

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../../interfaces/client';
import { Observable } from 'rxjs';
import { API_BASE_URL } from '../../config/config';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  getAllCLients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${API_BASE_URL}/clients`);
  }
}

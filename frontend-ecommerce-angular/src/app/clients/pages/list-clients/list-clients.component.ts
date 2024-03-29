import { Component, OnInit } from '@angular/core';
import { Client } from '../../../interfaces/client';
import { ClientService } from '../../service/client.service';


@Component({
  selector: 'app-list-clients',
  standalone: true,
  imports: [],
  templateUrl: './list-clients.component.html',
  styleUrl: './list-clients.component.css'
})
export class ListClientsComponent implements OnInit {
  clients: Client[] = [];

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(): void {
    this.clientService.getAllCLients().subscribe(
      (clients: Client[]) => {
        this.clients = clients;
        console.log("clients : ", clients)
      },
      (error) => {
        console.error('Error fetching clients:', error);
      }
    );
  }
}

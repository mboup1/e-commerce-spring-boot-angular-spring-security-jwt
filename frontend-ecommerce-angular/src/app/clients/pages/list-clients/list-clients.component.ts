import { Component } from '@angular/core';
import { ClientService } from '../../service/client.service';
import { Client } from '../../../interfaces/client';

@Component({
  selector: 'app-list-clients',
  templateUrl: './list-clients.component.html',
  styleUrl: './list-clients.component.css'
})
export class ListClientsComponent {
  clients: Client[] = [];

  constructor(private clientService: ClientService) { }

  ngOnInit(): void {
    this.getAllClients();
  }

  getAllClients(): void {
    this.clientService.getAllClients().subscribe(
      {
        next: (clients: Client[]) => {
          this.clients = clients;
          // console.log("clients : ", clients);
        },
        error: (error) => {
          console.error('Error fetching clients:', error);
        }
      }
    );
  }

  getClient(clientId: number): void {
    // Implémentez votre logique pour afficher les détails du client, par exemple :
    // this.router.navigate(['/details-client', clientId]);
  }

  updateClient(clientId: number): void {
    // Implémentez votre logique pour rediriger vers la page de mise à jour du client, par exemple :
    // this.router.navigate(['/update-client', clientId]);
  }

  onDeleteClient(clientId: number, firstName: string, lastName: string): void {
    // Implémentez votre logique pour supprimer le client, par exemple :
    let conf = confirm(`Etes-vous sûr de vouloir supprimer ${lastName} ${firstName} ?`);
    if (conf) {
      this.clientService.deleteClient(clientId).subscribe(() => {
        this.clients = this.clients.filter(client => client.id !== clientId);
        console.log("Client supprimé avec succès!");
      }, error => {
        console.error("Erreur lors de la suppression du client:", error);
      });
    }
  }

}

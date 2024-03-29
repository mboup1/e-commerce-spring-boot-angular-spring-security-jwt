import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../../service/client.service';

@Component({
  selector: 'app-add-client',
  // standalone: true,
  // imports: [],
  templateUrl: './add-client.component.html',
  styleUrl: './add-client.component.css'
})
export class AddClientComponent {
  // clientForm!: FormGroup;

  // constructor(private fb: FormBuilder, private clientService: ClientService) {
    
  //   this.clientForm = this.fb.group({
  //     companyName: [''],
  //     firstName: [''],
  //     lastName: [''],
  //     email: [''],
  //     phone: [''],
  //     address: [''],
  //     zipCode: [''],
  //     city: [''],
  //     country: [''],
  //     state: ['ACTIVE']
  //   });
  // }

  // createClient(): void {
  //   // if (this.clientForm.valid) {
  //     this.clientService.addClient(this.clientForm.value).subscribe(
  //       (client) => {
  //         console.log('Client added successfully:', client);
  //         // Vous pouvez également rediriger l'utilisateur vers une autre page après l'ajout du client
  //       },
  //       (error) => {
  //         console.error('Error adding client:', error);
  //       }
  //     );
  //   // }
  // }
}

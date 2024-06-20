package com.dame.ecommece.controller;

import com.dame.ecommece.entity.Client;
import com.dame.ecommece.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
//@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();

        return ResponseEntity.ok().body(clients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id).orElse(null);

        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Aucun client avec l'ID %d spécifié", id));
    }



    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody Client client) {
//        if (client.getCompanyName() == null || client.getCompanyName().isEmpty() ||
//                client.getFirstName() == null || client.getFirstName().isEmpty() ||
//                client.getLastName() == null || client.getLastName().isEmpty() ||
//                client.getEmail() == null || client.getEmail().isEmpty() ||
//                client.getPhone() == null || client.getPhone().isEmpty() ||
//                client.getAddress() == null || client.getAddress().isEmpty() ||
//                client.getZipCode() == null || client.getZipCode().isEmpty() ||
//                client.getCity() == null || client.getCity().isEmpty() ||
//                client.getCountry() == null || client.getCountry().isEmpty() ||
//                client.getState() == null) {
//            return ResponseEntity.badRequest().body("Tous les champs doivent être remplis");
//        }

        Client createdClient = clientService.addClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        if (updatedClient.getCompanyName() == null || updatedClient.getCompanyName().isEmpty() ||
                updatedClient.getFirstName() == null || updatedClient.getFirstName().isEmpty() ||
                updatedClient.getLastName() == null || updatedClient.getLastName().isEmpty() ||
                updatedClient.getEmail() == null || updatedClient.getEmail().isEmpty() ||
                updatedClient.getPhone() == null || updatedClient.getPhone().isEmpty() ||
                updatedClient.getAddress() == null || updatedClient.getAddress().isEmpty() ||
                updatedClient.getZipCode() == null || updatedClient.getZipCode().isEmpty() ||
                updatedClient.getCity() == null || updatedClient.getCity().isEmpty() ||
                updatedClient.getCountry() == null || updatedClient.getCountry().isEmpty() ||
                updatedClient.getState() == null) {
            return ResponseEntity.badRequest().body("Tous les champs doivent être remplis");
        }

        return clientService.updateClient(id, updatedClient)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

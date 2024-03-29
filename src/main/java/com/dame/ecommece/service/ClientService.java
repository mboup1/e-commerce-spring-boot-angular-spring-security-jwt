package com.dame.ecommece.service;

import com.dame.ecommece.entity.Client;
import com.dame.ecommece.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
//        System.out.println("add client");
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(Long id, Client updatedClient) {
        Optional<Client> existingClientOptional = clientRepository.findById(id);

        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            existingClient.setCompanyName(updatedClient.getCompanyName());
            existingClient.setFirstName(updatedClient.getFirstName());
            existingClient.setLastName(updatedClient.getLastName());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setPhone(updatedClient.getPhone());
            existingClient.setAddress(updatedClient.getAddress());
            existingClient.setZipCode(updatedClient.getZipCode());
            existingClient.setCity(updatedClient.getCity());
            existingClient.setCountry(updatedClient.getCountry());
            existingClient.setState(updatedClient.getState());

            return Optional.of(clientRepository.save(existingClient));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

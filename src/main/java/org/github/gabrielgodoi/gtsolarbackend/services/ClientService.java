package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.ClientDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.InsertClientDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDto create(InsertClientDto clientDto) {
        Client client = new Client();
        this.entityToDto(client, clientDto);
        Client clientEntity = this.clientRepository.save(client);

        return new ClientDto(clientEntity);
    }

    public List<ClientDto> findAll() {
        List<Client> clientList = this.clientRepository.findAll();
        return clientList.stream().map(ClientDto::new).toList();
    }

    public ClientDto update(String id, InsertClientDto clientDto) {
        Optional<Client> client = this.clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        this.entityToDto(client.get(), clientDto);

        this.clientRepository.insert(client.get());

        return new ClientDto(client.get());
    }

    public ClientDto findOne(String id) {
        Client client1 = this.clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new ClientDto(client1);
    }

    public void delete(String id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }
        this.clientRepository.deleteById(id);
    }

    public void entityToDto(Client entity, InsertClientDto dto) {
        entity.setName(dto.getName());
        entity.setStreet(dto.getStreet());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setDocument(dto.getDocument());
    }
}

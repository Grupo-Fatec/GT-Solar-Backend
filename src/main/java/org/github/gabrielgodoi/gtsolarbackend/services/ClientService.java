package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.ClientDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.InsertClientDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.externals.EmailService;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.ClientMapper;
import org.github.gabrielgodoi.gtsolarbackend.utils.emailBodies.EmailBodies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final EmailService emailService;
    private final EmailBodies emailBodies;

    public ClientDto create(InsertClientDto clientDto) {
        Client clientEntity = this.clientRepository.save(this.clientMapper.mapToEntity(clientDto));
        this.emailService.sendHtmlEmail(clientEntity.getEmail(), "Seja bem vindo a GT-solar", this.emailBodies.welcomeClientBody(clientEntity.getName(), clientEntity.getId()));
        return this.clientMapper.mapToDto(clientEntity);
    }

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public ClientDto update(String id, InsertClientDto clientDto) {
        this.clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user: " + clientDto.name() + " not found")
        );
        Client client = this.clientRepository.save(this.clientMapper.mapToEntity(clientDto));
        return this.clientMapper.mapToDto(client);
    }

    public ClientDto findOne(String id) {
        Client client1 = this.clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return clientMapper.mapToDto(client1);
    }

    public void delete(String id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client not found");
        }
        this.clientRepository.deleteById(id);
    }
}

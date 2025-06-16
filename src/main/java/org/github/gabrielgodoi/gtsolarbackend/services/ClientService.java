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
        Client retrievedData = this.clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("user: " + clientDto.name() + " not found")
        );

        retrievedData.setName(clientDto.name() == null ? retrievedData.getName() : clientDto.name());
        retrievedData.setStreet(clientDto.street() == null ? retrievedData.getStreet() : clientDto.street());
        retrievedData.setCep(clientDto.cep() == null ? retrievedData.getCep() : clientDto.cep());
        retrievedData.setUf(clientDto.uf() == null ? retrievedData.getUf() : clientDto.uf());
        retrievedData.setHouseNumber(clientDto.houseNumber() == null ? retrievedData.getHouseNumber() : clientDto.houseNumber());
        retrievedData.setNeighbor(clientDto.neighbor() == null ? retrievedData.getNeighbor() : clientDto.neighbor());
        retrievedData.setComplement(clientDto.complement() == null ? retrievedData.getComplement() : clientDto.complement());
        retrievedData.setEmail(clientDto.email() == null ? retrievedData.getEmail() : clientDto.email());
        retrievedData.setPhone(clientDto.phone() == null ? retrievedData.getPhone() : clientDto.phone());
        retrievedData.setDocument(clientDto.document() == null ? retrievedData.getDocument() : clientDto.document());
        retrievedData.setPropertyType(clientDto.propertyType() == null ? retrievedData.getPropertyType() : clientDto.propertyType());
        retrievedData.setRoofType(clientDto.roofType() == null ? retrievedData.getRoofType() : clientDto.roofType());

        // 🔒 Verificação de e-mail duplicado, para evitar erro 11000
        if (clientDto.email() != null && !clientDto.email().equals(retrievedData.getEmail())) {
            Optional<Client> existingEmail = clientRepository.findByEmail(clientDto.email());
            if (existingEmail.isPresent() && !existingEmail.get().getId().equals(retrievedData.getId())) {
                throw new IllegalArgumentException("E-mail já está em uso por outro cliente.");
            }
            retrievedData.setEmail(clientDto.email());
        }



        Client client = this.clientRepository.save(retrievedData);
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

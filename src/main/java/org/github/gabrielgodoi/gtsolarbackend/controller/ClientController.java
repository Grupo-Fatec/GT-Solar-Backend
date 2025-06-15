package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.ClientDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.InsertClientDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> clientDtoList = this.clientService.findAll();
        return ResponseEntity.ok().body(clientDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findOne(@PathVariable("id") String id) {
        ClientDto clientDto = this.clientService.findOne(id);
        return ResponseEntity.ok().body(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody InsertClientDto clientDto){
        ClientDto createdClient = this.clientService.create(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(createdClient.id())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(
            @PathVariable String id,
            @RequestBody InsertClientDto clientDto) {
        ClientDto updatedClient = clientService.update(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }
}

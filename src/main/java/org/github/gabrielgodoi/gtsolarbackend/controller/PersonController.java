package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.EngineerDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.InstallerDto;
import org.github.gabrielgodoi.gtsolarbackend.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    // ------- CREATE -------

    @PostMapping("/engineers")
    public ResponseEntity<EngineerDto> createEngineer(@RequestBody EngineerDto dto) {
        EngineerDto created = personService.createEngineer(dto);
        URI uri = URI.create("/persons/engineers/" + created.id());
        return ResponseEntity.created(uri).body(created);
    }

    @PostMapping("/installers")
    public ResponseEntity<InstallerDto> createInstaller(@RequestBody InstallerDto dto) {
        InstallerDto created = personService.createInstaller(dto);
        URI uri = URI.create("/persons/installers/" + created.id());
        return ResponseEntity.created(uri).body(created);
    }

    // ------- READ -------

    @GetMapping("/engineers")
    public ResponseEntity<List<EngineerDto>> findAllEngineers() {
        return ResponseEntity.ok(personService.findAllEngineers());
    }

    @GetMapping("/installers")
    public ResponseEntity<List<InstallerDto>> findAllInstallers() {
        return ResponseEntity.ok(personService.findAllInstaller());
    }

    @GetMapping("/engineers/{id}")
    public ResponseEntity<EngineerDto> findEngineerById(@PathVariable String id) {
        return ResponseEntity.ok(personService.findOneEngineer(id));
    }


    @GetMapping("/installers/{id}")
    public ResponseEntity<InstallerDto> findInstallerById(@PathVariable String id) {
        return ResponseEntity.ok(personService.findOneInstaller(id));
    }
    // ------- UPDATE -------

    @PutMapping("/engineers/{id}")
    public ResponseEntity<EngineerDto> updateEngineer(@PathVariable String id, @RequestBody EngineerDto dto) {
        return ResponseEntity.ok(personService.updateEngineer(id, dto));
    }

    @PutMapping("/installers/{id}")
    public ResponseEntity<InstallerDto> updateInstaller(@PathVariable String id, @RequestBody InstallerDto dto) {
        return ResponseEntity.ok(personService.updateInstaller(id, dto));
    }

    // ------- DELETE (Opcional) -------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        this.personService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}

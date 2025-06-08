package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.*;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.PersonRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;


    public List<EngineerDto> findAllEngineers() {
        List<Person> engineers = personRepository.findAllByType("ENGINEER");

        return engineers.stream()
                .filter(Engineer.class::isInstance)
                .map(p -> mapper.toDto((Engineer) p))
                .toList();
    }

    public EngineerDto findOneEngineer(String id) {
        return this.mapper.toDto(this.personRepository.findEngineerById(id).orElseThrow(
                () -> new EntityNotFoundException("Engineer with id: " + id + " was not found")
        ));
    }


    public EngineerDto createEngineer(EngineerDto dto) {
        Engineer entity = mapper.toEntity(dto);
        entity.setType("ENGINEER");
        return mapper.toDto((Engineer) personRepository.save(entity));
    }

    public EngineerDto updateEngineer(String id, EngineerDto dto) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Engineer not found with id: " + id)
        );

        if (!(person instanceof Engineer engineer)) {
            throw new IllegalArgumentException("Person with id " + id + " is not an Engineer");
        }

        engineer.setName(dto.name() != null ? dto.name() : engineer.getName());
        engineer.setEmail(dto.email() != null ? dto.email() : engineer.getEmail());
        engineer.setCrea(dto.crea() != null ? dto.crea() : engineer.getCrea());

        return mapper.toDto((Engineer) personRepository.save(engineer));
    }


    // installer

    public List<InstallerDto> findAllInstaller() {
        List<Person> installers = personRepository.findAllByType("INSTALLER");

        return installers.stream()
                .filter(Installer.class::isInstance)
                .map(p -> mapper.toDto((Installer) p))
                .toList();
    }

    public InstallerDto findOneInstaller(String id) {
        return this.mapper.toDto(this.personRepository.findInstallerById(id).orElseThrow(
                () -> new EntityNotFoundException("Installer with id: " + id + " was not found")
        ));
    }

    public InstallerDto createInstaller(InstallerDto dto) {
        Installer entity = mapper.toEntity(dto);
        entity.setType("INSTALLER");
        entity.setAvailable(true);
        return mapper.toDto((Installer) personRepository.save(entity));
    }

    public InstallerDto updateInstaller(String id, InstallerDto dto) {
        Installer person = (Installer) personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Installer not found with id: " + id));

        if (!(person instanceof Installer installer)) {
            throw new IllegalArgumentException("Person with id " + id + " is not an Installer");
        }

        installer.setName(dto.name() != null ? dto.name() : installer.getName());
        installer.setEmail(dto.email() != null ? dto.email() : installer.getEmail());
        installer.setPricePerKwp(dto.pricePerKwp() == null ? person.getPricePerKwp() : dto.pricePerKwp());
        installer.setAvailableDays(dto.availableDays() == null ? person.getAvailableDays() : dto.availableDays());

        return mapper.toDto((Installer) personRepository.save(installer));
    }

    public void delete(String id) {
        this.personRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        this.personRepository.deleteAllById(ids);
    }
}

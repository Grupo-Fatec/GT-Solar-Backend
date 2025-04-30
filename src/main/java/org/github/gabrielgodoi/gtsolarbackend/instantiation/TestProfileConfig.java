package org.github.gabrielgodoi.gtsolarbackend.instantiation;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.dto.commission.Commission;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.github.gabrielgodoi.gtsolarbackend.enums.PaymentStatus;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestProfileConfig implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
        this.clientRepository.deleteAll();
        this.adminRepository.deleteAll();
        Client client1 = new Client(
                null,
                "Alice Souza",
                "Rua das Flores, 123",
                "350 kWh",
                "alice.souza@email.com",
                "(11) 91234-5678",
                "123.456.789-00",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client client2 = new Client(
                null,
                "Bruno Oliveira",
                "Avenida Central, 456",
                "500 kWh",
                "bruno.oliveira@email.com",
                "(21) 92345-6789",
                "987.654.321-00",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client client3 = new Client(
                null,
                "Carla Lima",
                "Travessa dos Pinhais, 789",
                "280 kWh",
                "carla.lima@email.com",
                "(31) 93456-7890",
                "456.123.789-00",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client client4 = new Client(
                null,
                "Daniel Costa",
                "Alameda dos Anjos, 101",
                "420 kWh",
                "daniel.costa@email.com",
                "(41) 94567-8901",
                "789.123.456-00",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client client5 = new Client(
                null,
                "Eduarda Fernandes",
                "Praça da Liberdade, 202",
                "310 kWh",
                "eduarda.fernandes@email.com",
                "(51) 95678-9012",
                "321.654.987-00",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        this.clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4, client5));

        Admin admin1 = new Admin(
                null,
                "Gabriel Godoi",
                "gabriel.godoi@email.com",
                "senha123",
                AdminRole.SUPER_ADMIN,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin admin2 = new Admin(
                null,
                "Laura Martins",
                "laura.martins@email.com",
                "senha456",
                AdminRole.ADMIN,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin admin3 = new Admin(
                null,
                "Pedro Silva",
                "pedro.silva@email.com",
                "senha789",
                AdminRole.SUPPORT,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        // Then create and save commissions
        Commission commission1 = new Commission(1500.0, PaymentStatus.PENDING, LocalDateTime.now());
        Commission commission2 = new Commission(200.00, PaymentStatus.PAID, LocalDateTime.now());
        Commission commission3 = new Commission(2000.0, PaymentStatus.PENDING, LocalDateTime.now());

        admin1.getComissionsList().add(commission1);
        admin1.getComissionsList().add(commission2);
        admin3.getComissionsList().add(commission3);

        this.adminRepository.saveAll(Arrays.asList(admin1, admin2, admin3));
    }
}

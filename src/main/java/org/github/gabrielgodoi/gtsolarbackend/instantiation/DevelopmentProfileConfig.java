package org.github.gabrielgodoi.gtsolarbackend.instantiation;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ClientRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@Profile("development")
public class DevelopmentProfileConfig implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final ProjectRepository projectRepository;

    @Override
    public void run(String... args) throws Exception {
        this.clientRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.adminRepository.deleteAll();

        Client c1 = new Client(
                null,
                "João Silva",
                "123.456.789-00",
                "joao.silva@example.com",
                "(11) 91234-5678",
                "Rua das Flores",
                "123",
                "Apto 1",
                "Jardim Primavera",
                "São Paulo",
                "SP",
                "01001-000",
                "Telhado metálico",
                "RESIDENCIAL",
                "Cliente interessado em energia solar",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client c2 = new Client(
                null,
                "Maria Oliveira",
                "987.654.321-00",
                "maria.oliveira@example.com",
                "(21) 99876-5432",
                "Avenida Atlântica",
                "456",
                "Casa",
                "Copacabana",
                "Rio de Janeiro",
                "RJ",
                "22010-000",
                "Telha cerâmica",
                "COMERCIAL",
                "Cliente deseja financiamento",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client c3 = new Client(
                null,
                "Carlos Lima",
                "321.654.987-00",
                "carlos.lima@example.com",
                "(31) 91111-2222",
                "Rua das Palmeiras",
                "789",
                "",
                "Centro",
                "Belo Horizonte",
                "MG",
                "30140-000",
                "Laje",
                "INDUSTRIAL",
                "Tem urgência na instalação",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client c4 = new Client(
                null,
                "Fernanda Souza",
                "456.789.123-00",
                "fernanda.souza@example.com",
                "(41) 93333-4444",
                "Travessa das Acácias",
                "321",
                "Bloco B",
                "Santa Felicidade",
                "Curitiba",
                "PR",
                "82030-000",
                "Telhado metálico",
                "RESIDENCIAL",
                "Solicitou orçamento com urgência",
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Client c5 = new Client(
                null,
                "Bruno Mendes",
                "789.123.456-00",
                "bruno.mendes@example.com",
                "(51) 95555-6666",
                "Rua dos Ipês",
                "654",
                "em frente ao condomínio",
                "Bela Vista",
                "Porto Alegre",
                "RS",
                "90420-000",
                "Telhado metálico",
                "COMERCIAL",
                "Interessado em parceria",
                LocalDateTime.now(),
                LocalDateTime.now()
        );


        Admin ad1 = new Admin(
                null,
                "Alice Silva",
                "alice@admin.com",
                "senha123",
                AdminRole.SUPER_ADMIN,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad2 = new Admin(
                null,
                "Bruno Souza",
                "bruno@admin.com",
                "senha456",
                AdminRole.SUPPORT,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad3 = new Admin(
                null,
                "Carla Mendes",
                "carla@admin.com",
                "senha789",
                AdminRole.SELLER,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad4 = new Admin(
                null,
                "Diego Rocha",
                "diego@admin.com",
                "senha321",
                AdminRole.SUPPORT,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );


        this.adminRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4));
        this.clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
    }
}

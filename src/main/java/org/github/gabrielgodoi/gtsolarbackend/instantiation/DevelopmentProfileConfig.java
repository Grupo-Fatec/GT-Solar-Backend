package org.github.gabrielgodoi.gtsolarbackend.instantiation;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Supplier;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Engineer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Installer;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.github.gabrielgodoi.gtsolarbackend.enums.EquipmentType;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.github.gabrielgodoi.gtsolarbackend.repositories.*;
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
    private final PersonRepository personRepository;
    private final SupplierRepository supplierRepository;
    private final EquipmentRepository equipmentRepository;

    @Override
    public void run(String... args) throws Exception {
        this.personRepository.deleteAll();
        this.clientRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.adminRepository.deleteAll();
        this.supplierRepository.deleteAll();
        this.equipmentRepository.deleteAll();

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
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad2 = new Admin(
                null,
                "Bruno Souza",
                "bruno@admin.com",
                "senha456",
                AdminRole.SUPPORT,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad3 = new Admin(
                null,
                "Carla Mendes",
                "carla@admin.com",
                "senha789",
                AdminRole.SELLER,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        Admin ad4 = new Admin(
                null,
                "Diego Rocha",
                "diego@admin.com",
                "senha321",
                AdminRole.SUPPORT,
                LocalDateTime.now(),
                LocalDateTime.now()
        );


        Installer i1 = new Installer(
                null,
                "Carlos Silva",
                "INSTALLER",
                "carlos@exemplo.com",
                new ArrayList<>(),
                120.0,
                "Segunda a Sexta",
                true
        );

        Installer i2 = new Installer(
                null,
                "Fernanda Costa",
                "INSTALLER",
                "fernanda@exemplo.com",
                new ArrayList<>(),
                135.5,
                "Segunda, Quarta e Sexta",
                false
        );

        Installer i3 = new Installer(
                null,
                "João Mendes",
                "INSTALLER",
                "joao@exemplo.com",
                new ArrayList<>(),
                110.0,
                "Todos os dias",
                true
        );


        Engineer e1 = new Engineer(
                null,
                "Marcos Lima",
                "ENGINEER",
                "marcos@exemplo.com",
                new ArrayList<>(),
                "123456-CREA",
                "Projetos Elétricos",
                150.0
        );

        Engineer e2 = new Engineer(
                null,
                "Beatriz Rocha",
                "ENGINEER",
                "beatriz@exemplo.com",
                new ArrayList<>(),
                "789012-CREA",
                "Eficiência Energética",
                165.5
        );

        Engineer e3 = new Engineer(
                null,
                "Rafael Almeida",
                "ENGINEER",
                "rafael@exemplo.com",
                new ArrayList<>(),
                "345678-CREA",
                "Sistemas Fotovoltaicos",
                140.0
        );


        Supplier s1 = new Supplier(
                null,
                "GreenTech",
                "contato@greentech.com",
                "6 dias",
                new ArrayList<>()
        );

        Supplier s2 = new Supplier(
                null,
                "SolarParts Ltda",
                "vendas@solarparts.com",
                "7 dias",
                new ArrayList<>()
        );

        Supplier s3 = new Supplier(
                null,
                "EcoEnergy Supplies",
                "suporte@ecoenergy.com",
                "9 dias",
                new ArrayList<>()
        );

        this.supplierRepository.saveAll(Arrays.asList(s1, s2, s3));


        // Placas solares
        Equipment eq1 = new Equipment(null, "Placa Solar 450W JA Solar", EquipmentType.SOLAR_PANEL, "450W", 650.0, "12 anos", s1);
        Equipment eq2 = new Equipment(null, "Placa Solar 550W Canadian", EquipmentType.SOLAR_PANEL, "550W", 720.0, "10 anos", s1);
        Equipment eq3 = new Equipment(null, "Placa Solar 400W Trina", EquipmentType.SOLAR_PANEL, "400W", 600.0, "15 anos", s1);
        Equipment eq4 = new Equipment(null, "Placa Solar 470W Risen", EquipmentType.SOLAR_PANEL, "470W", 680.0, "12 anos", s2);
        Equipment eq5 = new Equipment(null, "Placa Solar 500W Longi", EquipmentType.SOLAR_PANEL, "500W", 710.0, "10 anos", s2);
        Equipment eq6 = new Equipment(null, "Placa Solar 540W Jinko", EquipmentType.SOLAR_PANEL, "540W", 730.0, "15 anos", s3);

        // Inversores
        Equipment eq7 = new Equipment(null, "Inversor Fronius 3kW", EquipmentType.INVERTER, "3000W", 2800.0, "10 anos", s3);
        Equipment eq8 = new Equipment(null, "Inversor Growatt 5kW", EquipmentType.INVERTER, "5000W", 3500.0, "12 anos", s3);
        Equipment eq9 = new Equipment(null, "Inversor Huawei 6kW", EquipmentType.INVERTER, "6000W", 4000.0, "15 anos", s2);

        this.equipmentRepository.saveAll(Arrays.asList(eq1, eq2, eq3, eq4, eq5, eq6, eq7, eq8, eq9));


        List<Equipment> equipamentosProjeto1 = Arrays.asList(eq1, eq7);
        List<Equipment> equipamentosProjeto2 = Arrays.asList(eq2, eq8, eq4);
        List<Equipment> equipamentosProjeto3 = Arrays.asList(eq3, eq9, eq5, eq6);
        Project p1 = new Project(
                null,
                "Projeto João - Residencial",
                StatusEnum.IN_PROGRES,
                450.0,
                false,
                false,
                200.00,
                ad1,
                e1,
                i1,
                c1,
                equipamentosProjeto1
        );

        Project p2 = new Project(
                null,
                "Projeto Maria - Comercial",
                StatusEnum.IN_PROGRES,
                1200.0,
                true,
                false,
                17500.0,
                ad2,
                e2,
                i2,
                c2,
                equipamentosProjeto2
        );

        Project p3 = new Project(
                null,
                "Projeto Carlos - Industrial",
                StatusEnum.DONE,
                5000.0,
                true,
                true,
                42000.0,
                ad3,
                e3,
                i3,
                c3,
                equipamentosProjeto3
        );


        s1.getEquipments().add(eq1);
        s1.getEquipments().add(eq2);
        s3.getEquipments().add(eq3);
        s2.getEquipments().add(eq4);
        s2.getEquipments().add(eq5);

        s3.getEquipments().add(eq6);
        s3.getEquipments().add(eq7);
        s3.getEquipments().add(eq8);
        s2.getEquipments().add(eq9);

        this.personRepository.saveAll(Arrays.asList(e1, e2, e3, i1, i2, i3));
        this.supplierRepository.saveAll(Arrays.asList(s1, s2, s3));
        this.adminRepository.saveAll(Arrays.asList(ad1, ad2, ad3, ad4));
        this.clientRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
        this.projectRepository.saveAll(Arrays.asList(p1, p2, p3));

    }
}

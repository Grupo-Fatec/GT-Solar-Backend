package org.github.gabrielgodoi.gtsolarbackend.controller.testes;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Supplier;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Engineer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Installer;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.github.gabrielgodoi.gtsolarbackend.enums.EquipmentType;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.github.gabrielgodoi.gtsolarbackend.services.externals.EmailService;
import org.github.gabrielgodoi.gtsolarbackend.utils.emailBodies.EmailBodies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailTest {
    private final EmailService emailService;
    private final EmailBodies emailBodies;

    @PostMapping("/test")
    public ResponseEntity<String> sendEmail(String to, String nameTester) {
        this.emailService.sendHtmlEmail(to, "Teste de envio de emails", this.emailBodies.welcomeClientBody(nameTester, ""));
        return ResponseEntity.ok().body("email sended");
    }

    @PostMapping("/attach")
    public ResponseEntity<String> sendEmailWithPdf(String to) {
        Project fakeProject = new Project(
                null,                                         // id
                "Projeto Fictício - Teste",                   // name
                StatusEnum.PLANNING,                           // status
                999.9,                                        // energyConsumption
                false,                                        // approvedByEngineer
                false,                                        // approvedByClient
                12345.67,                                     // approvedValue
                new Admin(                                    // createdBy
                        UUID.randomUUID().toString(),
                        "Fake Admin",
                        "fake@admin.com",
                        "123456",
                        AdminRole.SELLER,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Engineer(                                 // engineer
                        null,
                        "Fake Engineer",
                        "ENGINEER",
                        "fake.engineer@example.com",
                        new ArrayList<>(),
                        "CREA-0000",
                        "Especialidade Fictícia",
                        100.0
                ),
                new Installer(                                // installer
                        null,
                        "Fake Installer",
                        "INSTALLER",
                        "fake.installer@example.com",
                        new ArrayList<>(),
                        90.0,
                        "Seg-Sex",
                        true
                ),
                new Client(                                    // client
                        null,
                        "Cliente Teste",
                        "000.000.000-00",
                        "cliente@teste.com",
                        "(00) 90000-0000",
                        "Rua Fictícia",
                        "123",
                        "Apto X",
                        "Bairro Teste",
                        "Cidade Teste",
                        "UF",
                        "00000-000",
                        "Cobertura",
                        "RESIDENCIAL",
                        "Observação qualquer",
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                List.of(                                       // equipment list
                        new Equipment(
                                null,
                                "Painel Solar Fictício",
                                EquipmentType.SOLAR_PANEL,
                                "500W",
                                999.99,
                                "20 anos",
                                new Supplier(
                                        null,
                                        "Fornecedor Fictício",
                                        "contato@fornecedor.com",
                                        "5 dias úteis",
                                        new ArrayList<>()
                                )
                        ),
                        new Equipment(
                                null,
                                "Inversor Genérico",
                                EquipmentType.INVERTER,
                                "3000W",
                                1999.99,
                                "10 anos",
                                new Supplier(
                                        null,
                                        "Outro Fornecedor",
                                        "vendas@outro.com",
                                        "3 dias",
                                        new ArrayList<>()
                                )
                        )
                )
        );

        this.emailService.sendHtmlEmailWithAttachment(to, "teste de email com projeto", this.emailBodies.clientAcceptProjectBody("teste", ""),
                fakeProject
        );
        return ResponseEntity.ok().body("Email sended");
    }
}

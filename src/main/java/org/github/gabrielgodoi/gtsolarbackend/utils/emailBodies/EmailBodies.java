package org.github.gabrielgodoi.gtsolarbackend.utils.emailBodies;

import org.springframework.stereotype.Component;

@Component
public class EmailBodies {

    public String personAcceptProjectBody(String personName, String projectId) {
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                  </head>
                  <body>
                    <div style="font-family: Arial, sans-serif; padding: 20px;">
                      <h2 style="color: #4F8A6E;">Projeto Aguardando sua Aprovação</h2>
                      <p>Olá %s,</p>
                      <p>Você foi selecionado como responsável por um novo projeto. Deseja aceitar ou recusar a participação?</p>

                      <div style="margin-top: 30px; text-align: center;">
                        <a href="http://localhost:8081/project/engineer/accept/%s"
                           style="background-color: #4F8A6E; color: white; padding: 15px 40px; text-decoration: none; border-radius: 8px; display: inline-block;">
                          Acessar página de confirmação
                        </a>
                      </div>
                    </div>
                  </body>
                </html>
                """.formatted(personName, projectId);
    }

    public String clientAcceptProjectBody(String clientName, String projectId) {
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                  </head>
                  <body>
                    <div style="font-family: Arial, sans-serif; padding: 20px;">
                      <h2 style="color: #4F8A6E;">Confirmação de Projeto</h2>
                      <p>Olá %s,</p>
                      <p>Seu projeto foi criado e está aguardando sua aprovação. Por favor, revise os dados e confirme sua participação clicando abaixo:</p>

                      <div style="margin-top: 30px; text-align: center;">
                        <a href="http://localhost:8081/client/accept/%s"
                           style="background-color: #4F8A6E; color: white; padding: 15px 40px; text-decoration: none; border-radius: 8px; display: inline-block;">
                          Acessar página de confirmação
                        </a>
                      </div>
                    </div>
                  </body>
                </html>
                """.formatted(clientName, projectId);
    }

    public String welcomeClientBody(String clientName, String clientId) {
        return """
                <!DOCTYPE html>
                <html>
                  <head>
                  </head>
                  <body>
                    <div style='font-family: Arial, sans-serif; padding: 20px;'>
                      <h2 style='color: #4F8A6E;'>Bem-vindo à GT Solar!</h2>
                      <p>Olá %s,</p>
                      <p>Estamos felizes por tê-lo conosco! Seu cadastro foi realizado com sucesso.</p>

                      <div style='margin-top: 20px;'>
                        <a href='http://localhost:8081/client/confirm/%s' 
                           style='background-color: #4F8A6E; color: white; padding: 10px 20px; text-decoration: none; margin-right: 10px; border-radius: 5px;'>Confirmar Acesso</a>
                      </div>
                    </div>
                  </body>
                </html>
                """.formatted(clientName, clientId, clientId);
    }
}

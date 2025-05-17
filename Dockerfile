FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar

# Copia o script de entrada para o container
COPY entrypoint.sh /entrypoint.sh

# Dá permissão de execução ao script
RUN chmod +x /entrypoint.sh

EXPOSE 8082

# Usa o script como ponto de entrada
ENTRYPOINT ["/entrypoint.sh"]
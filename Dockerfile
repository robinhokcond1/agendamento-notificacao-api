# Usa uma imagem base que já tem o Maven instalado
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o código do projeto para dentro do container
COPY . .

# Compila o projeto com Maven
RUN mvn clean package -DskipTests

# Usa uma imagem mais leve para rodar a aplicação
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR compilado da etapa anterior para o contêiner final
COPY --from=build /app/target/agendamento-notificacao-api-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta do servidor
EXPOSE ${SERVER_PORT}

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]

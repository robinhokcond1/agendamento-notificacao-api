# Agendamento de Notificações API

## 📌 Descrição
Este projeto consiste em uma API REST desenvolvida com **Spring Boot** para gerenciar o agendamento de notificações. A API permite criar, listar e cancelar agendamentos, armazenando os dados em um banco de dados **PostgreSQL**. O projeto está configurado para execução via **Docker** e **Docker Compose**.

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **MapStruct**
- **Maven**
- **Docker & Docker Compose**

---

## ⚙️ Configuração do Ambiente

### 🔹 **1. Clonar o Repositório**
```sh
    git clone https://github.com/seu-repositorio/agendamento-notificacao-api.git
    cd agendamento-notificacao-api
```

### 🔹 **2. Criar Arquivo `.env` (Opcional, para configuração personalizada)**
Crie um arquivo **`.env`** na raiz do projeto para armazenar variáveis de ambiente:
```env
POSTGRES_DB=db_agendamento
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/db_agendamento
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SERVER_PORT=8081
TZ=America/Sao_Paulo
```

### 🔹 **3. Construir e Subir os Containers com Docker Compose**
```sh
    docker-compose up --build -d
```
Isso iniciará o **PostgreSQL** e a aplicação **Spring Boot**.

### 🔹 **4. Verificar os Logs da API**
```sh
    docker-compose logs -f app
```

### 🔹 **5. Testar a API via Swagger**
Acesse **`http://localhost:8081/swagger-ui.html`** para visualizar e testar os endpoints da API.

---

## 📡 Endpoints da API

### 🔹 **1. Criar um Novo Agendamento**
```http
POST /agendamento
```
**Body (JSON):**
```json
{
  "emailDestinatario": "email@teste.com",
  "telefoneDestinatario": "+553499999999",
  "mensagem": "Teste de agendamento",
  "dataHoraEnvio": "2025-02-10T12:30:00"
}
```

### 🔹 **2. Buscar um Agendamento por ID**
```http
GET /agendamento/{id}
```
**Exemplo:**
```sh
curl -X GET http://localhost:8081/agendamento/1
```

### 🔹 **3. Cancelar um Agendamento**
```http
PUT /agendamento/{id}/cancelar
```

---

## 🛠️ Estrutura do Projeto
```
agendamento-notificacao-api/
│-- src/
│   ├── main/
│   │   ├── java/com/javanauta/agendamento_notificacao_api/
│   │   │   ├── controller/    # Controllers da API
│   │   │   ├── business/      # Lógica de negócios (Service)
│   │   │   ├── dto/           # DTOs (Data Transfer Objects)
│   │   │   ├── entities/      # Entidades JPA
│   │   │   ├── mapper/        # MapStruct Mappers
│   │   │   ├── repositories/  # Interfaces JPA
│   │   │   ├── infrastructure/ # Configurações gerais
│   ├── test/  # Testes unitários
│-- Dockerfile  # Configuração do Docker
│-- docker-compose.yaml  # Configuração do Docker Compose
│-- README.md  # Documentação do Projeto
```

---

## ✅ Testes Unitários
Os testes são executados com **JUnit 5** e **Mockito**. Para rodar os testes:
```sh
    mvn test
```

---

## 📌 Como Parar e Remover os Containers
Caso precise interromper e remover os containers:
```sh
    docker-compose down
```
Para remover imagens e volumes não utilizados:
```sh
    docker system prune -a -f
    docker volume prune -f
```

---

## 🤝 Contribuição
Se quiser contribuir:
1. **Crie um Fork** do repositório.
2. **Crie uma nova branch** com sua funcionalidade/correção.
3. **Envie um Pull Request**.

---

## 📜 Licença
Este projeto está sob a licença MIT - sinta-se à vontade para utilizá-lo e melhorá-lo!

---

Agora sua API está documentada corretamente! 🚀🔥 Se precisar de ajustes, me avise! 😃


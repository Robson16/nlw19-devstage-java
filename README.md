# NLW 19 - DevStage - Java Back-End

Este projeto foi desenvolvido durante o evento NLW 19 Connect da Rocketseat. É uma API desenvolvida em Java com Spring Boot para o gerenciamento de eventos, utilizando JPA e banco de dados MySQL.

## 📋 Funcionalidades
- Cadastro de novos eventos.
- Consulta de todos os eventos.
- Busca de evento por `prettyName`.

## 🚀 Tecnologias Utilizadas
- **Java 21**: Linguagem de programação utilizada.
- **Spring Boot 3.4.2**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Abstração para persistência de dados.
- **MySQL**: Banco de dados relacional utilizado.
- **Maven**: Gerenciador de dependências e build.
- **Spring Boot DevTools**: Ferramenta para facilitar o desenvolvimento com recarregamento automático.

## 🛠️ Dependências do Projeto
- `spring-boot-starter-web`: Para desenvolvimento de APIs RESTful.
- `spring-boot-starter-data-jpa`: Para a integração com o banco de dados MySQL.
- `mysql-connector-j`: Driver JDBC para MySQL.
- `spring-boot-devtools`: Ferramentas de desenvolvimento.
- `spring-boot-starter-test`: Framework para testes.

## 📦 Estrutura do Projeto
O projeto é dividido em camadas para facilitar a manutenção e a escalabilidade:

- **Model**: Representação dos dados.
- **Repository**: Interface para acesso e manipulação de dados.
- **Service**: Regras de negócio.
- **Controller**: Rotas da API.

---

## 🔄 Rotas da API 

### Gerenciamento de Eventos

**POST /events**  
Cria um novo evento.

**Exemplo de corpo da requisição:**
```json
{
    "title": "CodeCraft Summit 2025",
    "location": "Online",
    "price": 0.0,
    "startDate": "2025-03-16",
    "endDate": "2025-03-18",
    "startTime": "19:00:00",
    "endTime": "21:00:00"
}
```

**GET /events**  
Retorna todos os eventos cadastrados.

**GET /events/{prettyName}**  
Retorna os detalhes de um evento específico pelo seu `prettyName`.

---

### Gerenciamento de Inscrições

**POST** `/subscription/{prettyName}`  
**POST** `/subscription/{prettyName}/{indicationUserId}`

Cria uma nova inscrição para um evento.

#### Parâmetros:
- `prettyName` (String) – Nome formatado do evento.
- `indicationUserId` (Integer, opcional) – ID do usuário que indicou a inscrição.
- `subscriber` (JSON) – Dados do usuário que deseja se inscrever.

#### Corpo da Requisição (Exemplo):
```json
{
  "name": "João Silva",
  "email": "joao.silva@email.com"
}
```

#### Respostas:
- **200 OK** – Inscrição criada com sucesso.
- **404 Not Found** – Evento ou usuário indicador não encontrado.
- **409 Conflict** – Usuário já inscrito no evento.

---

### Obter ranking de inscrições por evento

**GET** `/subscription/{prettyName}/ranking`

Retorna o ranking completo de inscrições para um evento específico.

#### Parâmetros:
- `prettyName` (String) – Nome formatado do evento.

#### Respostas:
- **200 OK** – Lista do ranking retornada com sucesso.
- **404 Not Found** – Evento não encontrado.

---

### Obter posição do usuário no ranking

**GET** `/subscription/{prettyName}/ranking/{userId}`

Retorna a posição do usuário no ranking de um evento específico.

#### Parâmetros:
- `prettyName` (String) – Nome formatado do evento.
- `userId` (Integer) – ID do usuário.

#### Respostas:
- **200 OK** – Posição do usuário no ranking retornada com sucesso.
- **404 Not Found** – Evento não encontrado ou usuário sem inscrição.

---

## ❗Exceções Personalizadas

- `EventNotFoundException`: Lançada quando o evento não é encontrado.
- `IndicationUserNotFoundException`: Lançada quando o usuário indicado não é encontrado.
- `SubscriptionConflictException`: Lançada quando já existe uma inscrição para o evento.

---

## 💻 Como Executar o Projeto

### Pré-requisitos
- Java 21 ou superior.
- MySQL instalado e configurado.
- Maven instalado.

### Passos
1. Clone este repositório:
   ```bash
   git clone https://github.com/robson16/nlw19-devstage-java.git
   cd nlw19-devstage-java
   ```

2. Configure o banco de dados MySQL e ajuste o arquivo `application.properties` conforme necessário.

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API em `http://localhost:8080`.

---

## 🔧 Estrutura do Banco de Dados

### Tabela: `tbl_event`
- `event_id` (PK): Identificador do evento.
- `title`: Título do evento.
- `location`: Localização do evento.
- `price`: Preço do evento.
- `start_date`: Data de início do evento.
- `end_date`: Data de término do evento.
- `start_time`: Horário de início do evento.
- `end_time`: Horário de término do evento.

### Tabela: `tbl_user`
- `user_id` (PK): Identificador do usuário.
- `name`: Nome do usuário.
- `email`: E-mail do usuário.

### Tabela: `tbl_subscription`
- `subscription_number` (PK): Número da inscrição.
- `event_id` (FK): Referência ao evento inscrito.
- `subscribed_user_id` (FK): Referência ao usuário inscrito.
- `indication_user_id` (FK, nullable): Referência ao usuário que indicou, se aplicável.

---

### 🌟 Créditos
Projeto desenvolvido durante o evento NLW 19 Connect da Rocketseat.

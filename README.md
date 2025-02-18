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

### 1. Criar um novo evento
**Endpoint:** `POST /events`  
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
**Resposta de sucesso:**  
- Código: `200 OK`
- Corpo: Objeto `Event` criado.

### 2. Listar todos os eventos
**Endpoint:** `GET /events`  
**Resposta de sucesso:**  
- Código: `200 OK`
- Corpo: Lista de todos os eventos cadastrados.

### 3. Buscar evento por `prettyName`
**Endpoint:** `GET /events/{prettyName}`  
**Parâmetro de rota:**  
- `prettyName`: Identificador amigável do evento.  
**Resposta de sucesso:**  
- Código: `200 OK`
- Corpo: Detalhes do evento correspondente.  
**Resposta de erro:**  
- Código: `404 Not Found`

---

## ⚙️ Como Executar o Projeto

### Pré-requisitos
- Java 21 ou superior.
- MySQL instalado e configurado.
- Maven instalado.

### Passos
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/nlw19-devstage-java.git
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

A tabela `events` possui os seguintes campos:
- `id`: Identificador único do evento.
- `title`: Título do evento.
- `location`: Local onde ocorrerá o evento.
- `price`: Preço do evento.
- `startDate` e `endDate`: Datas de início e término.
- `startTime` e `endTime`: Horários de início e término.
- `prettyName`: Identificador amigável para facilitar a busca.

---

## 📞 Suporte
Caso tenha dúvidas ou problemas, sinta-se à vontade para abrir uma issue ou entrar em contato!

---

### 🌟 Créditos
Projeto desenvolvido durante o evento NLW 19 Connect da Rocketseat.


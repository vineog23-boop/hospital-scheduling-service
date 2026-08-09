# Hospital Scheduling Service

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-4169E1)
![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Licença](https://img.shields.io/badge/licen%C3%A7a-n%C3%A3o%20definida-lightgrey)

API backend para o domínio hospitalar, iniciada como projeto prático de revisão de Spring Boot e base para as próximas entregas da pós-graduação na FIAP.

O checkpoint atual concentra a fundação do cadastro de pacientes: persistência com PostgreSQL, versionamento do schema com Flyway, entidade JPA, repositório Spring Data e contratos de entrada e saída com DTOs.

## Funcionalidades

- ✅ conexão da aplicação com PostgreSQL local;
- ✅ migration inicial da tabela `patients` com Flyway;
- ✅ entidade `Patient` com identidade UUID e auditoria de criação e atualização;
- ✅ repositório com Spring Data JPA;
- ✅ DTO de criação com Bean Validation;
- ✅ DTO de resposta sem exposição direta da entidade de persistência;
- 🚧 service e endpoints REST de pacientes;
- 🚧 tratamento padronizado de erros;
- 🚧 testes unitários e de integração do fluxo de pacientes;
- 🚧 autenticação e autorização com Spring Security;
- 🚧 mensageria e integrações previstas nas próximas etapas.

## Tecnologias

| Tecnologia | Versão / uso |
| --- | --- |
| Java | 21 |
| Spring Boot | 4.1.0 |
| Spring Web MVC | API REST |
| Spring Data JPA | Persistência |
| Hibernate | Mapeamento objeto-relacional |
| Bean Validation | Validação dos contratos de entrada |
| Flyway | Versionamento do schema |
| PostgreSQL | Banco de dados relacional |
| Maven Wrapper | Build e execução |

## Arquitetura atual

O código está organizado por funcionalidade. Dentro do módulo `patient`, cada pacote tem uma responsabilidade:

```text
src/main/java/br/com/viniciusoliveira/hospital/scheduling
├── HospitalSchedulingServiceApplication.java
└── patient
    ├── dto
    │   ├── request
    │   │   └── CreatePatientRequestDto.java
    │   └── response
    │       └── PatientResponseDto.java
    ├── entity
    │   └── Patient.java
    └── repository
        └── PatientRepository.java
```

Essa estrutura mantém os contratos da API separados da entidade JPA. As próximas camadas serão adicionadas dentro de `patient` conforme os comportamentos forem implementados, evitando pacotes globais sem contexto de negócio.

## Pré-requisitos

- JDK 21;
- PostgreSQL em execução;
- Git;
- porta `5432` livre para o PostgreSQL, ou ajuste correspondente na configuração;
- porta `8080` livre para a aplicação.

Não é necessário instalar o Maven: o projeto inclui Maven Wrapper.

## Configuração do banco de dados

Crie localmente o banco usado pelo projeto:

```sql
CREATE DATABASE hospital_scheduling;
```

O arquivo `src/main/resources/application.properties` é ignorado pelo Git para impedir o versionamento de credenciais locais. Configure-o na sua máquina com valores equivalentes a estes:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_scheduling
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_LOCAL

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false
```

O Flyway executa automaticamente as migrations presentes em `src/main/resources/db/migration`. O Hibernate usa `validate` para conferir o mapeamento, sem criar ou alterar tabelas por conta própria.

## Como executar sem Docker

Clone o repositório e entre no diretório do projeto:

```bash
git clone https://github.com/vineog23-boop/hospital-scheduling-service.git
cd hospital-scheduling-service
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Com a aplicação iniciada, o servidor HTTP fica disponível em `http://localhost:8080`.

## Como executar com Docker

Ainda não há `Dockerfile` nem `compose.yaml` neste checkpoint. A execução atual utiliza Java e PostgreSQL instalados localmente. O suporte a containers será adicionado em uma etapa posterior e deverá ser documentado somente quando estiver funcional.

## Endpoints

Ainda não há endpoints HTTP expostos. O próximo incremento será o cadastro de pacientes, passando por controller, service e repository e devolvendo `PatientResponseDto`.

| Método | Rota planejada | Descrição | Status |
| --- | --- | --- | --- |
| `POST` | `/api/v1/patients` | Cadastrar um paciente | 🚧 Não implementado |

Os exemplos de requisição e resposta serão incluídos quando o contrato HTTP estiver implementado e validado.

## Testes

Execute a suíte com:

```powershell
.\mvnw.cmd test
```

No estado atual, a suíte valida a inicialização do contexto Spring. Os próximos incrementos devem adicionar:

- testes unitários das regras no service;
- testes de integração dos endpoints;
- testes de persistência contra PostgreSQL real quando a infraestrutura de testes for introduzida.

## Decisões e aprendizados

- `localhost` identifica a própria máquina; `5432` é a porta usada pelo servidor PostgreSQL e `8080` é a porta HTTP da aplicação.
- O Flyway é o responsável por evoluir o schema por migrations versionadas. Uma migration já aplicada não deve ser editada; mudanças futuras recebem uma nova versão.
- A entidade possui construtor vazio protegido para o JPA e um construtor de negócio apenas com os dados necessários para criar um paciente.
- `@PrePersist` e `@PreUpdate` preenchem os timestamps no ciclo de vida da entidade.
- DTOs em formato `record` representam contratos imutáveis e evitam expor a entidade JPA na borda da API.
- O arquivo local de configuração não é versionado porque contém credenciais do ambiente de desenvolvimento.

## Roadmap

1. Implementar `PatientService` e o endpoint `POST /api/v1/patients`.
2. Mapear `CreatePatientRequestDto` para `Patient` e `Patient` para `PatientResponseDto`.
3. Padronizar erros de validação e conflito de e-mail.
4. Adicionar testes unitários e de integração.
5. Implementar autenticação e autorização com Spring Security.
6. Evoluir o domínio hospitalar e preparar integrações de mensageria.

## Autor

Vinícius Oliveira — [GitHub](https://github.com/vineog23-boop)

## Licença

Este projeto ainda não possui uma licença de uso definida.

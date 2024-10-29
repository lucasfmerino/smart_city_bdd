# Projeto de Testes BDD - Smart City API
Este projeto realiza testes **BDD (Behavior-Driven Development)** para a API Smart City.
Utilizando **Cucumber, RestAssured e JSON Schema Validator**, os testes incluem validações de login, cadastro de usuários e verificação de conformidade de contrato JSON.

## Estrutura do Projeto
A estrutura do projeto está organizada da seguinte forma:

```bash
smart_city_bdd
├── .github
│   └── workflows
│       └── ci.yaml                      # Arquivo de integração contínua (CI) no GitHub Actions
├── src
│   └── test
│       ├── java
│       │   ├── config
│       │   │   └── DatabaseConfig        # Configuração de banco de dados (exemplo)
│       │   ├── hook
│       │   │   └── Hook                  # Hook para limpeza de dados no banco após cada teste
│       │   ├── model
│       │   │   ├── LoginModel            # Modelo de dados para login
│       │   │   └── UserModel             # Modelo de dados para cadastro de usuário
│       │   ├── runner
│       │   │   └── TestRunner            # Classe para execução dos testes
│       │   ├── services
│       │   │   ├── LoginUserService      # Serviço para manipulação de login
│       │   │   └── RegisterUserService   # Serviço para manipulação de cadastro
│       │   └── steps
│       │       ├── LoginUserSteps        # Definição dos passos para testes de login
│       │       └── RegisterUserSteps     # Definição dos passos para testes de cadastro
│       └── resources
│           ├── features
│           │   ├── Login.feature         # Arquivo de cenários de teste de login
│           │   ├── RegisterUser.feature  # Arquivo de cenários de teste de cadastro
│           │   └── UserContract.feature  # Arquivo de cenário de teste de contrato JSON
│           └── schemas
│               └── UserContract.json     # Esquema JSON para validação de contrato de usuário
└── pom.xml                               # Arquivo de configuração do Maven com dependências

```

## Funcionalidades e Cenários

### Login de Usuário
Arquivo: `Login.feature`

Testa o fluxo de login para um usuário registrado na API.

- Contexto: Cadastro de usuário
- Cenário: Login bem-sucedido de usuário
  - Dado que o usuário tenha credenciais válidas.
  - Quando ele faz uma requisição para o endpoint /auth/login.
  - Então o status code deve ser 200.
  - E a resposta deve conter um token JWT.
  
### Cadastro de Usuário
Arquivo: `RegisterUser.feature`

Testa o fluxo de cadastro de um novo usuário na API.

- Cenário: Cadastro bem-sucedido de usuário
  - Dado que o usuário tenha os dados necessários para cadastro.
  - Quando ele faz uma requisição para o endpoint /auth/register.
  - Então o status code deve ser 201.

### Validação de Contrato de Usuário
Arquivo: `UserContract.feature`

Testa a conformidade da resposta da API com o contrato JSON esperado.

- Cenário: Validação de contrato de usuário
  - Dado que o usuário tenha os dados necessários para cadastro.
  - Quando ele faz uma requisição para o endpoint /auth/register.
  - Então o status code deve ser 201.
  - E a resposta deve estar em conformidade com o contrato JSON definido.
  
## Configuração e Execução
### Requisitos
- Java 21
- Maven para gerenciamento de dependências

## Dependências Principais
As principais dependências usadas neste projeto incluem:

- **Cucumber**: para cenários de BDD
- **RestAssured**: para requisições HTTP nos testes
- **JSON Schema Validator**: para validação de contrato JSON

As dependências estão listadas no arquivo `pom.xml`.

## Executando os Testes
Para executar todos os testes, use o comando Maven:

```bash
mvn clean test
```

## Configuração de Banco de Dados
- A classe DatabaseConfig permite configurar as credenciais de banco de dados.
- Um Hook foi implementado para limpar o banco de dados após cada teste.

## Integração Contínua
- O arquivo ci.yaml configura a integração contínua via GitHub Actions para que os testes sejam executados automaticamente em cada push.
- #### Para funcionar a integração é necessário configurar a classe `DatabaseConfig.java`
#language: pt

@regressivo
Funcionalidade: Validar contrato no cadastro de usuário

  Cenário: Validar contrato cadastro de usuário bem sucedido
    Dado que eu tenha os seguintes dados de usuário:
      | campo                 | valor          |
      | name                  | Test           |
      | email                 | test@email.com |
      | password              | Test@123       |
      | role                  | ADMIN          |
      | accountNonExpired     | true           |
      | credentialsNonExpired | true           |
      | accountNonLocked      | true           |
      | enabled               | true           |
    Quando eu enviar a requisição para o endpoint "/auth/register" de cadastro de usuário
    Então o status code da resposta deve ser 201
    E que o arquivo de contrato esperado é o "UserContract"
    Então a resposta da requisição deve estar em conformidade com o contrato selecionado

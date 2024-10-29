# language:pt

@regressivo
Funcionalidade: Login de usuário
  Como usuário da API
  Quero conseguir logar
  Para que eu possa utilizar a plataforma

  Contexto: : Cadastro bem-sucedido de usuário
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

  Cenário:  Deve ser possível logar na plataforma
    Dado que eu tenha os seguintes dados de login:
      | campo                 | valor          |
      | email                 | test@email.com |
      | password              | Test@123       |
    Quando eu enviar a requisição para o endpoint "/auth/login" de login de usuário
    Então o status code da resposta de login deve ser 200
    E a resposta deve conter um token JWT
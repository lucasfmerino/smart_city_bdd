# language:pt

@regressivo
Funcionalidade: Cadastro de novo usuário
  Quero cadastrar um usuário na plataforma
  Para que o registro seja salvo corretamente no sistema

  Cenário: Cadastro bem-sucedido de usuário
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


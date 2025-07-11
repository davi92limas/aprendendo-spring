# aprendendo-spring

Projeto de exemplo utilizando Spring Boot 3, Java 21 e PostgreSQL, com foco em autenticação JWT, cadastro de usuários e
estruturação de uma API REST segura.

## Visão Geral

Este projeto implementa uma API REST para gerenciamento de usuários, incluindo autenticação, cadastro, atualização e
remoção, além de entidades relacionadas como endereço e telefone. O sistema utiliza Spring Security com JWT para
autenticação e autorização, e Spring Data JPA para acesso ao banco de dados PostgreSQL.

## Principais Funcionalidades

- Cadastro, listagem, atualização e deleção de usuários
- Entidades relacionadas: Usuário, Endereço, Telefone
- Segurança com autenticação JWT
- Tratamento de exceções customizadas
- Separação em camadas: Controller, Service, Repository, DTOs, Security

## Estrutura do Projeto

```
├── src/main/java/com/davi/aprendendospring
│   ├── AprendendoSpringApplication.java         # Classe principal (main)
│   ├── business/UsuarioService.java             # Regras de negócio de usuário
│   ├── controller/UsuarioController.java        # Endpoints REST de usuário
│   ├── controller/dtos/UsuarioDTO.java          # Data Transfer Object de usuário
│   └── infrastructure
│       ├── entity/Endereco.java                 # Entidade Endereço
│       ├── entity/Telefone.java                 # Entidade Telefone
│       ├── entity/Usuario.java                  # Entidade Usuário
│       ├── exceptions/ConflitExeption.java      # Exceção de conflito
│       ├── exceptions/ResourceNotFoundExeception.java # Exceção de não encontrado
│       ├── repository/EnderecoRepository.java   # Repository Endereço
│       ├── repository/TelefoneRepository.java   # Repository Telefone
│       ├── repository/UsuarioRepository.java    # Repository Usuário
│       └── security
│           ├── JwtRequestFilter.java            # Filtro JWT
│           ├── JwtUtil.java                     # Utilitário JWT
│           ├── SecurityConfig.java              # Configuração do Spring Security
│           └── UserDetailsServiceImpl.java      # Implementação do UserDetailsService
├── src/main/resources
│   ├── application.properties                   # Configurações do projeto
│   ├── static/                                  # Recursos estáticos (opcional)
│   └── templates/                               # Templates (opcional)
├── pom.xml                                      # Dependências Maven
└── docker-compose.yml                           # (Opcional) Configuração Docker
```

## Como rodar o projeto

1. Certifique-se de ter o PostgreSQL rodando e configure o banco conforme o `application.properties`.
2. Compile e execute o projeto:
   ```sh
   ./mvnw spring-boot:run
   ```
3. Acesse a API em: http://localhost:8090

## Configuração do Banco

No arquivo `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/aprendendo_spring
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Autenticação

A autenticação é baseada em JWT. Para acessar endpoints protegidos, obtenha um token via login e envie no header
`Authorization: Bearer <token>`.

## Observações

- O projeto segue boas práticas de separação de camadas.
- Exceções customizadas facilitam o tratamento de erros na API.
- O código é ideal para aprendizado e como base para projetos mais robustos.


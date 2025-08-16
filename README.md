# Fórum Hub

Fórum Hub é uma aplicação backend construída com **Java**, **Spring Boot** e **Spring Security**, que simula o funcionamento de um fórum de discussão, inspirado no Fórum da Alura. O projeto permite que usuários cadastrados criem tópicos, respondam dúvidas e gerenciem conteúdo de forma segura.

## Funcionalidades

- Cadastro e autenticação de usuários com **JWT (Bearer Token)**.
- Criação de tópicos com título, mensagem e vinculação a cursos.
- Listagem de tópicos e detalhes de cada tópico.
- Exclusão de tópicos (somente pelo autor).
- Segurança aplicada para impedir alterações ou exclusões por usuários não autorizados.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **JWT (JSON Web Token)**
- **Lombok** (para simplificar código)
- **Maven** (gerenciamento de dependências)

## Estrutura de Pacotes

- `controller`: endpoints REST da aplicação.
- `dto`: objetos de transferência de dados (requests e responses).
- `model`: entidades do banco de dados (Usuario, Topico, Curso).
- `repository`: interfaces de acesso ao banco.
- `service`: regras de negócio da aplicação.
- `security`: configuração do Spring Security e JWT.

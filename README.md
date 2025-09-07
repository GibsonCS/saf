# Sistema Administrativo de Reserva de Salas e Gestão de Equipamentos

Este projeto consiste em um sistema administrativo para controle de reservas de salas e gestão de equipamentos, desenvolvido para facilitar o gerenciamento de espaços e recursos em ambientes institucionais.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Thymeleaf**
- **Lombok**
- **Banco de Dados MySQL ou PostgreSQL**
- **Maven**
- **Heroku (deploy)**

## Relação das Tabelas

- **SALA**: possui várias **LOCAÇÕES**
- **LOCAÇÃO**: pode incluir vários **EQUIPAMENTOS**
- **PESSOA**: pode reservar várias **LOCAÇÕES**
- **USER**: realiza **LOCAÇÕES** e possui **ROLES** (perfis)

Principais campos:

- SALA: id, nomeSala
- LOCACAO: id, data, horaInicial
- EQUIPAMENTO: id, nomeEquipamento, modeloEquipamento, numeroSerie, marcaEquipamento, tipoEquipamento
- PESSOA: id, cpf, nome, sobrenome
- USER: id, nomeCompleto, username
- ROLE: id, name

## Usuário e Senha Padrão

- **Usuário:** admin
- **Senha:** master123

## Como Executar o Projeto

1. Gere o JAR do projeto:
   ```sh
   mvn clean package
   ```
2. Execute o JAR gerado:
   ```sh
   java -jar target/saf-0.0.1-SNAPSHOT.jar
   ```
3. Acesse o sistema pelo navegador:
   ```
   http://localhost:8888
   ```

Se for usar Heroku, siga os passos do README para deploy.

## Diagrama das Relações de Tabelas (Mermaid)

```mermaid
erDiagram
    SALA ||--o{ LOCACAO : "possui"
    LOCACAO ||--|{ EQUIPAMENTO : "inclui"
    PESSOA ||--o{ LOCACAO : "reserva"
    USER ||--o{ LOCACAO : "realiza"
    USER ||--o{ ROLE : "possui"

    PESSOA {
        id
        cpf
        nome
        sobrenome
    }
    SALA {
        id
        nomeSala
    }
    LOCACAO {
        id
        data
        horaInicial
    }
    EQUIPAMENTO {
        id
        nomeEquipamento
        modeloEquipamento
        numeroSerie
        marcaEquipamento
        tipoEquipamento
    }
    USER {
        id
        nomeCompleto
        username
    }
    ROLE {
        id
        name
    }

```

## Diagrama das Relações das Entidades

```mermaid
classDiagram
    class Sala {
        +Long id
        +String nomeSala
    }
    class Locacao {
        +Long id
        +Date data
        +LocalTime horaInicial
        +LocalTime horaFinal
        +String evento
        +String descricao
    }
    class Equipamento {
        +Long id
        +String nomeEquipamento
        +String modeloEquipamento
        +String numeroSerie
        +String marcaEquipamento
        +String tipoEquipamento
    }
    class Pessoa {
        +Long id
        +String cpf
        +String nome
        +String sobrenome
        +String telefone
    }
    class User {
        +Long id
        +String nomeCompleto
        +String username
        +String password
        +String email
        +String cpf
        +String rg
    }
    class Role {
        +Long id
        +String name
    }
    class Endereco {
        +Long id
        +String cep
        +String uf
        +String cidade
        +String bairro
        +String endereco
        +long numero
        +String complemento
    }

    Sala "1" --> "*" Locacao : possui
    Locacao "*" --> "1" Sala : pertence
    Locacao "*" --> "1" Pessoa : reservadaPor
    Locacao "1" --> "*" Equipamento : inclui
    Equipamento "*" --> "1" Pessoa : pertenceA
    Pessoa "1" --> "*" Locacao : reservas
    Pessoa "1" --> "*" Equipamento : equipamentos
    User "1" --> "*" Endereco : enderecos
    User "*" --> "*" Role : roles
```

# Drone Feeder
O Drone Feeder é uma aplicação REST de um sistema de delivery que permitirá a entrega de pacotes com drones. Ao ser utilizado, ele é capaz de exibir algumas informações de drones, tais como latitude e longitude, data e horário da entrega ou retirada do pacote, permitindo operações de CRUD.

## Tabela de Conteúdos

- [Visão geral](#visão-geral)
    - [O desafio](#o-desafio)
- [Processo de desenvolvimento](#processo-de-desenvolvimento)
    - [Construção](#construção)
- [Usabilidade](#usabilidade)
    - [Build](#build)
    - [Endpoints](#endpoints)
        - [/drones](#drones)
        - [/deliveries](#deliveries)
        - [/videos](#videos)
- [Autores](#autores)


## Visão geral

### O desafio

O Drone Feeder foi pensado para a prática geral dos conhecimentos sobre uma aplicação em Java com Spring Boot, cobrindo tópicos como: SOLID, REST e POO.

#### Ao consumir a API você será capaz de:
- Registrar drones e entregas;
- Verificar todos os drones, entregas e vídeos registrados;
- Atulizar os dados dos drones;
- Deletar drones;
- Cancelar entregas;
- Alterar o status dos drones e das entregas.

## Processo de desenvolvimento

### Construção:

- Java
- Spring
- Docker
- MySql
- Maven

## Usabilidade

### Build:
* Faça o download do projeto ou clone através do comando `git clone :HttpOuSSH`;
* Crie um arquivo `.env` para criar suas variáveis de ambiente com as seguintes chaves:
```
DB_USER=
DB_ROOT_PASSWORD=

APP_DOCKER_PORT=
DB_DOCKER_PORT=
DB_LOCAL_PORT=
APP_LOCAL_PORT=
```
* Instale as dependências na raiz do projeto com o comando `mvn install`;
* Use o comando `mvn spring-boot:run` na raiz do projeto para abrir o projeto, ele de ficará disponivel em *localhost:8080*;
* Caso queira subir toda a aplicação em containers do docker, use o comando `docker-compose up` na raiz do projeto.

#### Nota: Lembre-se de configurar as variáveis de ambiente de acordo com as configurações do seu sistema ou haverá erros no *build* da aplicação.

### Endpoints:
### ```/drones```

#### @GET
##### Response:
```
[
    {
        "id": "5b6e16ef-5d4c-4656-88db-8f658e5fdc36",
        "name": "drone1",
        "latitude": 2.0,
        "longitude": 2.0,
        "status": "IDLE"
    },
    ...
]
```

#### @GET ```/{id}```
##### Response:
```
{
    "id": "5b6e16ef-5d4c-4656-88db-8f658e5fdc36",
    "name": "drone1",
    "latitude": 2.0,
    "longitude": 2.0,
    "status": "IDLE"
}
```

#### @POST
##### Body:
```
{
    "name": "drone1",
    "latitude": 2.0,
    "longitude": 2.0
}
```
##### Response:
```
{
    "id": "5b6e16ef-5d4c-4656-88db-8f658e5fdc36",
    "name": "drone1",
    "latitude": 2.0,
    "longitude": 2.0,
    "status": "IDLE"
}
```

#### @PATCH ```/{id}/start/{deliveryId}```
##### Response:
```
{
    "id": "5b6e16ef-5d4c-4656-88db-8f658e5fdc36",
    "name": "drone1att",
    "latitude": 204.0,
    "longitude": 224.0,
    "status": "DELIVERING"
}
```

#### @PUT ```/{id}```
##### Body:
```
{
    "name": "drone1att",
    "latitude": 204.0,
    "longitude": 224.0
}
```
##### Response:
```
{
    "id": "5b6e16ef-5d4c-4656-88db-8f658e5fdc36",
    "name": "drone1att",
    "latitude": 204.0,
    "longitude": 224.0,
    "status": "IDLE"
}
```

#### @DELETE ```/{name}```
##### Response:
``` ```
#
### ```/deliveries```
#### @GET
##### Response:
```
[
    {
        "id": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a",
        "status": "IN_PROGRESS",
        "createdAt": "20/10/2022 02:39:47",
        "orderDate": "20/10/2022 02:42:45",
        "deliveryDate": null,
        "cancelDate": null,
        "videoId": null
    },
    ...
]
```

#### @POST ```/{droneId}```
##### Response:
```
{
    "id": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a",
    "status": "PENDING",
    "createdAt": "20/10/2022 02:39:47",
    "orderDate": null,
    "deliveryDate": null,
    "cancelDate": null
}
```

#### @PATCH ```/{id}/finish```
##### Body:
```
{
    "videoData": [10, 20, 10, 20]
}
```
##### Response:
```
{
    "id": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a",
    "status": "DELIVERED",
    "createdAt": "20/10/2022 02:39:47",
    "orderDate": "20/10/2022 02:42:45",
    "deliveryDate": "20/10/2022 03:00:15",
    "cancelDate": null,
    "videoId": "d41d4c3a-8eaa-4b6e-b1c4-3dfb5fb4d682"
}
```

#### @PATCH ```/{id}/cancel```
##### Response:
```
{
    "id": "c8c39dba-faf3-4bd1-8eb6-da0f89a6f108",
    "status": "CANCELLED",
    "createdAt": "20/10/2022 02:56:58",
    "orderDate": null,
    "deliveryDate": null,
    "cancelDate": "20/10/2022 02:57:23"
}
```

#
### ```/videos```
#### @GET
##### Response:
```
[
    {
        "id": "d41d4c3a-8eaa-4b6e-b1c4-3dfb5fb4d682",
        "data": [
            10,
            20,
            10,
            20
        ],
        "deliveryId": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a"
    },
    ...
]
```

#### @GET ```/{id}```
##### Response:
```
{
    "id": "d41d4c3a-8eaa-4b6e-b1c4-3dfb5fb4d682",
    "data": [
        10,
        20,
        10,
        20
    ],
    "deliveryId": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a"
}
```

#### @GET ```/delivery/{deliveryId}```
##### Response:
```
{
    "id": "d41d4c3a-8eaa-4b6e-b1c4-3dfb5fb4d682",
    "data": [
        10,
        20,
        10,
        20
    ],
    "deliveryId": "945d407d-bf72-4b7c-82be-d6f7ae3dab1a"
}
```

## Autores:

### LinkedIn
- [Gabriel Silvestre](https://www.linkedin.com/in/gabrielh-silvestre/)
- [Gustavo Lemes](https://www.linkedin.com/in/gustavo-v-lemes/)
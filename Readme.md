# Gestion de usuarios v1.0

Este microservicio es responsable de la gestión de usuarios a través de sus datos básicos.

## Requisitos infraestructura

- Java 11+
- Maven 3.6+
- Base de datos H2 (iguals e pueda suar cualquier otra)

## Construcción y ejecución

### Construcción

Ejecuta el siguiente comando para construir el proyecto usando Maven:

```sh
mvn clean install


### Ejecución

Una vez que el proyecto ha sido construido con éxito, estando dentro del directorio del proeycto, puedes lanzarlo utilizando el siguiente comando:

```sh
java -jar target/nombre-del-archivo.jar


Prueba del API:
--------------

Swagger UI
Una vez que la aplicación está en ejecución, puedes navegar a http://localhost:8003/doc/swagger-ui.html para ver y probar los endpoints del API a través de Swagger UI.

Endpoints
Descripción de los endpoints principales:

POST /api/usuarios: Crea un nuevo usuario.
GET /api/usuarios: Obtiene una lista de todos los usuarios.
GET /api/usuarios/{id}: Obtiene los detalles de un usuario específico.

Descripción de las excepciones principales:
--------------------------------------------

EmailAlreadyRegisteredException: Se lanza cuando se intenta registrar un email que ya existe en el sistema.

InvalidFormatException: Se lanza cuando se intenta registrar un email o password que no cumplen con el formato especificado en las expresiones regfulares configuradas en las variables del archivo application.properties




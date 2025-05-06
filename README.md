# Fuego de Quasar

Este proyecto es una API REST desarrollada con Spring Boot para determinar la ubicación y el mensaje de una nave a partir de la información recibida por tres satélites.

## Enunciado

Se debe determinar:
- La **posición** de una nave portacarga imperial, usando triangulación basada en las distancias desde tres satélites con posiciones definidas.
- El **mensaje** que la nave envía, reconstruido a partir de fragmentos parciales (palabras) recibidos por los satélites.

## Tecnologías

- Java 8
- Spring Boot
- Maven
- JUnit 5

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/org/rebeldes/fuegodequasar/
│   │   ├── controller/          # Controladores REST
│   │   ├── dto/                 # Objetos de transferencia
│   │   ├── model/               # Entidades como Posicion y Satelite
│   │   └── service/             # Lógica del proyecto (mensaje y triangulación)
│   └── resources/
│       └── application.properties
├── test/
│   └── java/org/rebeldes/fuegodequasar/
│       └── service/             # Pruebas unitarias
```

## Endpoints

- `POST /topsecret`
  - Recibe un JSON con los tres satélites, sus distancias y fragmentos del mensaje.
  - Devuelve la posición de la nave y el mensaje reconstruido.
  - Devuelve status code 404 si no se puede determinar.

- `POST /topsecret_split/{satelite}`
  - Almacena la distancia a la nave y el mensaje recibido por un satélite.

- `GET /topsecret_split`
  - Procesa los mensajes y distancias almacenados, devuelve la posición y el mensaje de la nave.
  - Devuelve status code 404 si no puede determinar posición, mensaje o si faltan satélites por introducir.

## Cómo Ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/utalotta/meli-examen.git
   cd meli-examen
   ```

2. Compilar y ejecutar con Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Acceder al tomcat (corre desde spring):
   ```
   http://localhost:8080
   ```

## Pruebas

Se incluyen pruebas unitarias para:
- Reconstrucción de mensaje con diferentes alineaciones.
- Cálculo de posición usando trilateración.

Ejecutar:
```bash
mvn test
```

---

Muchas gracias 😊

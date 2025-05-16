# API REST de Gestión de Citas Veterinarias

Este proyecto es una API REST desarrollada con Spring Boot para la gestión de citas veterinarias. Permite administrar citas, veterinarias y servicios veterinarios.

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## Requisitos Previos

- Java 17 o superior
- Maven
- PostgreSQL
- IDE (recomendado: IntelliJ IDEA o Eclipse)

## Configuración del Proyecto

1. Clonar el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Configurar la base de datos:
   - Crear una base de datos PostgreSQL
   - Actualizar las credenciales en `src/main/resources/application.properties`

3. Compilar el proyecto:
```bash
mvn clean install
```

4. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

## Estructura del Proyecto

```
src/main/java/com/citas/
├── controller/
│   ├── CitaController.java
│   ├── VeterinariaController.java
│   └── ServicioController.java
├── model/
│   ├── Cita.java
│   ├── Veterinaria.java
│   ├── Servicio.java
│   └── EstadoCita.java
├── repository/
│   ├── CitaRepository.java
│   ├── VeterinariaRepository.java
│   └── ServicioRepository.java
├── service/
│   ├── CitaService.java
│   ├── VeterinariaService.java
│   └── ServicioService.java
└── CitasApplication.java
```

## Modelo de Datos

### Veterinaria
```java
@Entity
@Table(name = "veterinarias")
public class Veterinaria {
    private Long idveterinaria;
    private String nombreveterinaria;
    private String direccionveterinaria;
    private String telefonoveterinaria;
    private String emailveterinaria;
    private Boolean activoveterinaria;
}
```

### Servicio
```java
@Entity
@Table(name = "servicios")
public class Servicio {
    private Long idservicio;
    private Long idveterinaria;
    private String nombreservicio;
    private Double precioservicio;
    private Boolean activoservicio;
}
```

### Cita
```java
@Entity
@Table(name = "citas")
public class Cita {
    private Long idcita;
    private Long idveterinaria;
    private Long idcliente;
    private Long idmascota;
    private Long idservicio;
    private LocalDate fecha;
    private LocalTime horainicio;
    private LocalTime horafinal;
    private EstadoCita estadocita;
}
```

### Estados de Cita
Los estados posibles para una cita son:
- `PENDIENTE`: Cita recién creada, esperando confirmación
- `CONFIRMADA`: Cita confirmada por la veterinaria
- `CANCELADA`: Cita cancelada por el cliente o la veterinaria
- `COMPLETADA`: Cita realizada exitosamente
- `NO_ASISTIO`: Cliente no asistió a la cita

### Mascota
```java
@Entity
@Table(name = "mascotas")
public class Mascota {
    private Long idmascota;
    private Long idcliente;
    private String nombremascota;
    private String especiemascota;
    private String razamascota;
    private Integer edadmascota;
    private Double pesomascota;
    private Boolean activomascota;
}
```

## Endpoints de la API

### Veterinarias

#### Obtener todas las veterinarias
```http
GET /api/veterinarias
```
Respuesta:
```json
[
    {
        "idveterinaria": 1,
        "nombreveterinaria": "Veterinaria Central",
        "direccionveterinaria": "Calle Principal 123",
        "telefonoveterinaria": "123-456-7890",
        "emailveterinaria": "contacto@veterinaria.com",
        "activoveterinaria": true
    }
]
```

#### Obtener veterinarias activas
```http
GET /api/veterinarias/activas
```

#### Buscar veterinarias por nombre
```http
GET /api/veterinarias/buscar?nombre=Central
```

#### Obtener una veterinaria por ID
```http
GET /api/veterinarias/{id}
```

#### Crear una nueva veterinaria
```http
POST /api/veterinarias
```
Cuerpo de la petición:
```json
{
    "nombreveterinaria": "Veterinaria Central",
    "direccionveterinaria": "Calle Principal 123",
    "telefonoveterinaria": "123-456-7890",
    "emailveterinaria": "contacto@veterinaria.com",
    "activoveterinaria": true
}
```

#### Actualizar una veterinaria
```http
PUT /api/veterinarias/{id}
```
Cuerpo de la petición:
```json
{
    "nombreveterinaria": "Veterinaria Central Actualizada",
    "direccionveterinaria": "Calle Principal 123",
    "telefonoveterinaria": "123-456-7890",
    "emailveterinaria": "nuevo@veterinaria.com",
    "activoveterinaria": true
}
```

#### Eliminar una veterinaria
```http
DELETE /api/veterinarias/{id}
```

### Servicios

#### Obtener todos los servicios
```http
GET /api/servicios
```
Respuesta:
```json
[
    {
        "idservicio": 1,
        "nombreservicio": "Consulta General",
        "precioservicio": 50.00,
        "activoservicio": true
    }
]
```

#### Obtener servicios activos
```http
GET /api/servicios/activos
```

#### Buscar servicios por nombre
```http
GET /api/servicios/buscar?nombre=Consulta
```

#### Obtener un servicio por ID
```http
GET /api/servicios/{id}
```

#### Crear un nuevo servicio
```http
POST /api/servicios
```
Cuerpo de la petición:
```json
{
    "idveterinaria": 1,
    "nombreservicio": "Consulta General",
    "precioservicio": 50.00,
    "activoservicio": true
}
```

#### Actualizar un servicio
```http
PUT /api/servicios/{id}
```
Cuerpo de la petición:
```json
{
    "nombreservicio": "Consulta General Actualizada",
    "precioservicio": 60.00,
    "activoservicio": true
}
```

#### Eliminar un servicio
```http
DELETE /api/servicios/{id}
```

### Citas

#### Obtener todas las citas
```http
GET /api/citas
```
Respuesta:
```json
[
    {
        "idcita": 1,
        "idveterinaria": 1,
        "idcliente": 1,
        "idmascota": 1,
        "idservicio": 1,
        "fecha": "2023-10-01",
        "horainicio": "10:00:00",
        "horafinal": "11:00:00",
        "estadocita": "PENDIENTE"
    }
]
```

#### Obtener una cita por ID
```http
GET /api/citas/{id}
```

#### Obtener citas por cliente
```http
GET /api/citas/cliente/{idcliente}
```

#### Obtener citas por veterinaria
```http
GET /api/citas/veterinaria/{idveterinaria}
```

#### Obtener citas por mascota
```http
GET /api/citas/mascota/{idmascota}
```

#### Crear una nueva cita
```http
POST /api/citas
```
Cuerpo de la petición:
```json
{
    "idveterinaria": 1,
    "idcliente": 1,
    "idmascota": 1,
    "idservicio": 1,
    "fecha": "2023-10-01",
    "horainicio": "10:00:00",
    "horafinal": "11:00:00",
    "estadocita": "PENDIENTE"
}
```

#### Actualizar una cita
```http
PUT /api/citas/{id}
```
Cuerpo de la petición:
```json
{
    "estadocita": "CONFIRMADA"
}
```

#### Eliminar una cita
```http
DELETE /api/citas/{id}
```

### Mascotas

#### Obtener todas las mascotas
```http
GET /api/mascotas
```
Respuesta:
```json
[
    {
        "idmascota": 1,
        "idcliente": 1,
        "nombremascota": "Rex",
        "especiemascota": "Perro",
        "razamascota": "Labrador",
        "edadmascota": 5,
        "pesomascota": 25.5,
        "activomascota": true
    }
]
```

#### Obtener mascotas activas
```http
GET /api/mascotas/activas
```

#### Obtener mascotas por cliente
```http
GET /api/mascotas/cliente/1
```

#### Buscar mascotas por nombre
```http
GET /api/mascotas/buscar?nombre=Rex
```

#### Buscar mascotas por especie
```http
GET /api/mascotas/buscar/especie?especie=Perro
```

#### Obtener una mascota por ID
```http
GET /api/mascotas/{id}
```

#### Crear una nueva mascota
```http
POST /api/mascotas
```
Cuerpo de la petición:
```json
{
    "idcliente": 1,
    "nombremascota": "Rex",
    "especiemascota": "Perro",
    "razamascota": "Labrador",
    "edadmascota": 5,
    "pesomascota": 25.5,
    "activomascota": true
}
```

#### Actualizar una mascota
```http
PUT /api/mascotas/{id}
```
Cuerpo de la petición:
```json
{
    "nombremascota": "Rex Actualizado",
    "edadmascota": 6,
    "activomascota": true
}
```

#### Eliminar una mascota
```http
DELETE /api/mascotas/{id}
```

## Ejemplos de Consumo de APIs con Postman

### Veterinarias

1. **Obtener todas las veterinarias**
   - Método: GET
   - URL: `http://localhost:8080/api/veterinarias`

2. **Obtener veterinarias activas**
   - Método: GET
   - URL: `http://localhost:8080/api/veterinarias/activas`

3. **Buscar veterinarias por nombre**
   - Método: GET
   - URL: `http://localhost:8080/api/veterinarias/buscar?nombre=Central`

4. **Obtener una veterinaria por ID**
   - Método: GET
   - URL: `http://localhost:8080/api/veterinarias/1`

5. **Crear una nueva veterinaria**
   - Método: POST
   - URL: `http://localhost:8080/api/veterinarias`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "nombreveterinaria": "Veterinaria Central",
         "direccionveterinaria": "Calle Principal 123",
         "telefonoveterinaria": "123-456-7890",
         "emailveterinaria": "contacto@veterinaria.com",
         "activoveterinaria": true
     }
     ```

6. **Actualizar una veterinaria**
   - Método: PUT
   - URL: `http://localhost:8080/api/veterinarias/1`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "nombreveterinaria": "Veterinaria Central Actualizada",
         "direccionveterinaria": "Calle Principal 123",
         "telefonoveterinaria": "123-456-7890",
         "emailveterinaria": "nuevo@veterinaria.com",
         "activoveterinaria": true
     }
     ```

7. **Eliminar una veterinaria**
   - Método: DELETE
   - URL: `http://localhost:8080/api/veterinarias/1`

### Servicios

1. **Obtener todos los servicios**
   - Método: GET
   - URL: `http://localhost:8080/api/servicios`

2. **Obtener servicios activos**
   - Método: GET
   - URL: `http://localhost:8080/api/servicios/activos`

3. **Buscar servicios por nombre**
   - Método: GET
   - URL: `http://localhost:8080/api/servicios/buscar?nombre=Consulta`

4. **Obtener un servicio por ID**
   - Método: GET
   - URL: `http://localhost:8080/api/servicios/1`

5. **Crear un nuevo servicio**
   - Método: POST
   - URL: `http://localhost:8080/api/servicios`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "idveterinaria": 1,
         "nombreservicio": "Consulta General",
         "precioservicio": 50.00,
         "activoservicio": true
     }
     ```

6. **Actualizar un servicio**
   - Método: PUT
   - URL: `http://localhost:8080/api/servicios/1`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "nombreservicio": "Consulta General Actualizada",
         "precioservicio": 60.00,
         "activoservicio": true
     }
     ```

7. **Eliminar un servicio**
   - Método: DELETE
   - URL: `http://localhost:8080/api/servicios/1`

### Citas

1. **Obtener todas las citas**
   - Método: GET
   - URL: `http://localhost:8080/api/citas`

2. **Obtener una cita por ID**
   - Método: GET
   - URL: `http://localhost:8080/api/citas/1`

3. **Crear una nueva cita**
   - Método: POST
   - URL: `http://localhost:8080/api/citas`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "idveterinaria": 1,
         "idcliente": 1,
         "idmascota": 1,
         "idservicio": 1,
         "fecha": "2023-10-01",
         "horainicio": "10:00:00",
         "horafinal": "11:00:00",
         "estadocita": "PENDIENTE"
     }
     ```

4. **Actualizar una cita**
   - Método: PUT
   - URL: `http://localhost:8080/api/citas/1`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "estadocita": "CONFIRMADA"
     }
     ```

5. **Eliminar una cita**
   - Método: DELETE
   - URL: `http://localhost:8080/api/citas/1`

### Mascotas

1. **Obtener todas las mascotas**
   - Método: GET
   - URL: `http://localhost:8080/api/mascotas`

2. **Obtener mascotas activas**
   - Método: GET
   - URL: `http://localhost:8080/api/mascotas/activas`

3. **Buscar mascotas por nombre**
   - Método: GET
   - URL: `http://localhost:8080/api/mascotas/buscar?nombre=Rex`

4. **Obtener una mascota por ID**
   - Método: GET
   - URL: `http://localhost:8080/api/mascotas/1`

5. **Crear una nueva mascota**
   - Método: POST
   - URL: `http://localhost:8080/api/mascotas`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "idcliente": 1,
         "nombremascota": "Rex",
         "especiemascota": "Perro",
         "razamascota": "Labrador",
         "edadmascota": 5,
         "pesomascota": 25.5,
         "activomascota": true
     }
     ```

6. **Actualizar una mascota**
   - Método: PUT
   - URL: `http://localhost:8080/api/mascotas/1`
   - Headers: `Content-Type: application/json`
   - Body:
     ```json
     {
         "nombremascota": "Rex Actualizado",
         "edadmascota": 6,
         "activomascota": true
     }
     ```

7. **Eliminar una mascota**
   - Método: DELETE
   - URL: `http://localhost:8080/api/mascotas/1`

## Ejecución del Proyecto

### Desde el IDE (IntelliJ IDEA o Eclipse)

1. Abre el proyecto en tu IDE
2. Localiza la clase principal `CitasApplication.java`
3. Haz clic derecho sobre la clase y selecciona "Run CitasApplication"
   - NO ejecutes `CitasApplicationTests.java` a menos que quieras ejecutar las pruebas

### Desde la Línea de Comandos

```bash
# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```

### Verificar que la Aplicación está Funcionando

1. Abre tu navegador o Postman
2. Accede a: `http://localhost:8080/api/veterinarias`
3. Deberías ver una respuesta JSON (probablemente un array vacío si no hay datos)

## Solución de Problemas Comunes

### La Aplicación no Inicia

Si ves errores al iniciar, verifica:

1. Que la base de datos PostgreSQL esté accesible
2. Que las credenciales en `application.properties` sean correctas
3. Que el puerto 8080 no esté en uso

### Errores de Conexión a la Base de Datos

Si ves errores de conexión:

1. Verifica que la URL de la base de datos sea correcta
2. Confirma que el usuario y contraseña sean válidos
3. Asegúrate de que la base de datos esté en ejecución

### Warnings Comunes

#### Warning de PostgreSQLDialect
```
WARN: PostgreSQLDialect does not need to be specified explicitly
```
Este warning es inofensivo y ya está resuelto en la configuración actual.

#### Warning de Java Agent
```
WARNING: A Java agent has been loaded dynamically
```
Este warning es normal durante las pruebas y no afecta el funcionamiento de la aplicación.
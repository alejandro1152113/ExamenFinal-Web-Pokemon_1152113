# 🎮 Pokémon REST API — Examen Final Web

API REST desarrollada con **Spring Boot 3.3** para gestionar Pokémon, Entrenadores, Pueblos y Tipos de Pokémon en el mundo Pokémon.

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Versión | Descripción |
|---|---|---|
| Java | 17+ | Lenguaje principal |
| Spring Boot | 3.3.0 | Framework principal |
| Spring Data JPA | 3.3.0 | Acceso a datos / ORM |
| Hibernate | 6.5.2 | Implementación JPA |
| **H2 Database** | Runtime | Base de datos en memoria |
| Springdoc OpenAPI | 2.5.0 | Documentación Swagger UI |
| Maven | 3.9+ | Gestión de dependencias |

---

## 🗄️ Base de Datos — H2 (En Memoria)

El proyecto utiliza **H2 Database**, una base de datos **relacional embebida en memoria** (in-memory). Esto significa que:

- ✅ **No requiere instalación** de ningún motor de base de datos externo (MySQL, PostgreSQL, etc.)
- ✅ **Se inicia automáticamente** al arrancar la aplicación Spring Boot
- ✅ **Se inicializa con datos de prueba** al arrancar (via `schema.sql` y `data.sql`)
- ⚠️ Los datos **se pierden al detener** la aplicación (es in-memory)

### Esquema de Base de Datos

```
pokemon_pueblo          pokemon_tipo_pokemon
──────────────          ────────────────────
id (PK)                 id (PK)
nombre                  descripcion
uuid                    uuid

pokemon_entrenador      pokemon_pokemon
──────────────────      ───────────────
id (PK)                 id (PK)
nombre                  nombre
apellido                descripcion
email                   tipo_pokemon (FK → pokemon_tipo_pokemon)
fecha_nacimiento        fecha_descubrimiento
fecha_vinculacion       generacion
pueblo_id (FK)          uuid
uuid

pokemon_captura  (tabla intermedia N:M)
────────────────
pokemon_id    (FK → pokemon_pokemon)
entrenador_id (FK → pokemon_entrenador)
```

### Datos Iniciales (Seed)

Al arrancar la aplicación se insertan automáticamente:

| Tabla | Datos |
|---|---|
| `pokemon_pueblo` | Pueblo Paleta |
| `pokemon_tipo_pokemon` | Agua, Planta, Fuego |
| `pokemon_entrenador` | **Ash Ketchum** (`ash@gmail.com`) |
| `pokemon_pokemon` | Bulbasaur (Agua), Charmander (Fuego) |
| `pokemon_captura` | Ash → Bulbasaur |

### 🖥️ Consola H2 (Interfaz Web de la BD)

Puedes **visualizar y consultar la base de datos en tiempo real** desde el navegador:

```
URL:      http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:pokemondb
Usuario:  sa
Password: (dejar vacío)
```

> ⚠️ La aplicación debe estar corriendo para acceder a la consola.

**Consultas SQL de ejemplo:**
```sql
-- Ver todos los pokémon
SELECT * FROM pokemon_pokemon;

-- Ver todos los entrenadores
SELECT * FROM pokemon_entrenador;

-- Ver capturas (relación entrenador ↔ pokémon)
SELECT e.nombre, p.nombre as pokemon
FROM pokemon_captura c
JOIN pokemon_entrenador e ON e.id = c.entrenador_id
JOIN pokemon_pokemon p ON p.id = c.pokemon_id;
```

---

## 📖 Documentación Swagger / OpenAPI

El proyecto incluye **Swagger UI** generado automáticamente con Springdoc OpenAPI. Permite explorar y probar todos los endpoints directamente desde el navegador.

```
Swagger UI:   http://localhost:8080/swagger-ui/index.html
OpenAPI JSON: http://localhost:8080/v3/api-docs
```

> ⚠️ La aplicación debe estar corriendo para acceder a Swagger.

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos previos
- Java 17 o superior instalado
- Maven 3.6+ instalado

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/alejandro1152113/ExamenFinal-Web-Pokemon_1152113.git
cd ExamenFinal-Web-Pokemon_1152113

# 2. Ejecutar con Maven
mvn spring-boot:run
```

La aplicación arrancará en: **http://localhost:8080**

---

## 📡 Endpoints de la API

### 1. `POST /entrenador/login`
Autenticar un entrenador por email y obtener su UUID.

**Request:**
```json
{
  "email": "ash@gmail.com"
}
```

**Response exitoso (200 OK):**
```json
{
  "uuid": "f3262c24-473d-437d-a5cf-e87673637954"
}
```

**Response fallido (401 Unauthorized):** *(cuerpo vacío)*

---

### 2. `GET /pokemons/{tipo}`
Obtener lista de Pokémon por tipo. El parámetro `{tipo}` puede ser:
- El **nombre** del tipo: `/pokemons/Agua`
- El **ID** del tipo: `/pokemons/1`
- El **UUID** del tipo: `/pokemons/123456`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombre": "Bulbasaur",
    "descripcion": "Planta",
    "generacion": 1,
    "uuid": "12345678-1234-1234-1234-1234567890",
    "tipo_pokemon": { "id": 1, "descripcion": "Agua", "uuid": "123456" },
    "fecha_descubrimiento": "2023/01/01"
  }
]
```

---

### 3. `POST /pokemons`
Registrar un nuevo Pokémon.

**Request:**
```json
{
  "nombre": "Squirtle",
  "descripcion": "Pokémon de agua",
  "fecha_descubrimiento": "2023/05/10",
  "generacion": 1,
  "tipo_pokemon": "Agua"
}
```

**Response exitoso (201 Created):**
```json
{
  "id": 3,
  "nombre": "Squirtle",
  "descripcion": "Pokémon de agua",
  "generacion": 1,
  "uuid": "94c4d7a0-...",
  "tipo_pokemon": { "id": 1, "descripcion": "Agua", "uuid": "123456" },
  "fecha_descubrimiento": "2023/05/10"
}
```

**Response error (400 Bad Request):**
```json
{ "error": "true", "message": "Tipo de Pokemon no encontrado" }
```

---

### 4. `GET /entrenador/{uuid}/pokemons`
Obtener la lista de Pokémon capturados de un entrenador.

**Response exitoso (200 OK):** Lista de Pokémon del entrenador.

**Response error (404 Not Found):**
```json
{ "error": "true", "message": "Entrenador no encontrado" }
```

---

### 5. `POST /entrenador/{uuid}/pokemons/{pokemonUuid}`
Asociar un Pokémon a un entrenador.

**Response exitoso (200 OK):** Lista actualizada de Pokémon del entrenador.

**Response — Pokémon ya registrado (400):**
```json
{ "error": "true", "message": "pokemon ya esta registrado al entrenador" }
```

**Response — No encontrado (404):**
```json
{ "error": "true", "message": "Entrenador no encontrado" }
```

---

## 🔧 Configuración CORS

La API tiene **CORS habilitado globalmente** para todos los orígenes (`*`), lo que permite ser consumida desde cualquier frontend (React, Angular, Vue, etc.) sin restricciones de dominio.

---

## 📁 Estructura del Proyecto

```
src/
└── main/
    ├── java/com/pokemon/
    │   ├── Application.java                  # Punto de entrada Spring Boot
    │   ├── config/
    │   │   └── CorsConfig.java               # Configuración CORS global
    │   ├── controller/
    │   │   ├── EntrenadorController.java      # Endpoints /entrenador/**
    │   │   └── PokemonController.java         # Endpoints /pokemons/**
    │   ├── dto/
    │   │   ├── LoginRequest.java
    │   │   ├── LoginResponse.java
    │   │   └── CreatePokemonRequest.java
    │   ├── model/
    │   │   ├── Entrenador.java
    │   │   ├── Pokemon.java
    │   │   ├── Pueblo.java
    │   │   └── TipoPokemon.java
    │   └── repository/
    │       ├── EntrenadorRepository.java
    │       ├── PokemonRepository.java
    │       ├── PuebloRepository.java
    │       └── TipoPokemonRepository.java
    └── resources/
        ├── application.properties            # Configuración H2, Swagger, Jackson
        ├── schema.sql                        # Creación de tablas (DDL)
        └── data.sql                          # Datos iniciales (seed)
```

---

## 📅 Formato de Fechas

Todas las fechas en la API usan el formato **`yyyy/MM/dd`**:
```
"fecha_descubrimiento": "2023/05/10"
"fecha_nacimiento": "1997/04/01"
```

---

*Examen Final — Programación Web | Estudiante: alejandro1152113*

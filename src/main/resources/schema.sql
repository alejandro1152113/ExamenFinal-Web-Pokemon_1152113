DROP TABLE IF EXISTS pokemon_captura;
DROP TABLE IF EXISTS pokemon_pokemon;
DROP TABLE IF EXISTS pokemon_entrenador;
DROP TABLE IF EXISTS pokemon_tipo_pokemon;
DROP TABLE IF EXISTS pokemon_pueblo;

CREATE TABLE pokemon_pueblo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    uuid VARCHAR(100) NOT NULL
);

CREATE TABLE pokemon_tipo_pokemon (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    uuid VARCHAR(100) NOT NULL
);

CREATE TABLE pokemon_entrenador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    fecha_vinculacion DATE NOT NULL,
    pueblo_id INT NOT NULL,
    uuid VARCHAR(100) NOT NULL,
    FOREIGN KEY (pueblo_id) REFERENCES pokemon_pueblo(id)
);

CREATE TABLE pokemon_pokemon (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    tipo_pokemon INT NOT NULL,
    fecha_descubrimiento DATE NOT NULL,
    generacion INT NOT NULL,
    uuid VARCHAR(100) NOT NULL,
    FOREIGN KEY (tipo_pokemon) REFERENCES pokemon_tipo_pokemon(id)
);

CREATE TABLE pokemon_captura (
    pokemon_id INT NOT NULL,
    entrenador_id INT NOT NULL,
    PRIMARY KEY (pokemon_id, entrenador_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon_pokemon(id),
    FOREIGN KEY (entrenador_id) REFERENCES pokemon_entrenador(id)
);

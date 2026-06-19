INSERT INTO pokemon_pueblo (id, nombre, uuid) VALUES 
(1, 'Pueblo Paleta', 'pueblo-paleta-uuid-1');

INSERT INTO pokemon_tipo_pokemon (id, descripcion, uuid) VALUES 
(1, 'Agua', '123456'),
(2, 'Planta', 'planta-uuid-2'),
(3, 'Fuego', 'fuego-uuid-3');

INSERT INTO pokemon_entrenador (id, nombre, apellido, email, fecha_nacimiento, fecha_vinculacion, pueblo_id, uuid) VALUES 
(1, 'Ash', 'Ketchum', 'ash@gmail.com', '1997-04-01', '2023-01-01', 1, 'f3262c24-473d-437d-a5cf-e87673637954');

INSERT INTO pokemon_pokemon (id, nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) VALUES 
(1, 'Bulbasaur', 'Planta', 1, '2023-01-01', 1, '12345678-1234-1234-1234-1234567890'),
(2, 'Charmander', 'Fuego', 3, '2023-01-02', 1, 'charmander-uuid-unique-123');

INSERT INTO pokemon_captura (pokemon_id, entrenador_id) VALUES 
(1, 1);

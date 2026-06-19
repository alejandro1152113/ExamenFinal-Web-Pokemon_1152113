INSERT INTO pokemon_pueblo (nombre, uuid) VALUES 
('Pueblo Paleta', 'pueblo-paleta-uuid-1');

INSERT INTO pokemon_tipo_pokemon (descripcion, uuid) VALUES 
('Agua', '123456'),
('Planta', 'planta-uuid-2'),
('Fuego', 'fuego-uuid-3');

INSERT INTO pokemon_entrenador (nombre, apellido, email, fecha_nacimiento, fecha_vinculacion, pueblo_id, uuid) VALUES 
('Ash', 'Ketchum', 'ash@gmail.com', '1997-04-01', '2023-01-01', 1, 'f3262c24-473d-437d-a5cf-e87673637954');

INSERT INTO pokemon_pokemon (nombre, descripcion, tipo_pokemon, fecha_descubrimiento, generacion, uuid) VALUES 
('Bulbasaur', 'Planta', 1, '2023-01-01', 1, '12345678-1234-1234-1234-1234567890'),
('Charmander', 'Fuego', 3, '2023-01-02', 1, 'charmander-uuid-unique-123');

INSERT INTO pokemon_captura (pokemon_id, entrenador_id) VALUES 
(1, 1);

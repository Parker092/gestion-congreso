docker exec -it postgres-congreso psql -U congresoadmin -d gestion_congreso -c "SELECT * FROM congresista; SELECT * FROM trabajo; SELECT * FROM autor_trabajo; SELECT t.id AS trabajo_id, t.titulo, t.resumen, c.id AS congresista_id, c.nombre, c.apellido FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id JOIN congresista c ON at.congresista_id = c.id; SELECT * FROM sesion; SELECT p.id AS presentacion_id, s.sala, s.fecha_hora, t.titulo AS trabajo_titulo, c.nombre AS ponente FROM presentacion p JOIN sesion s ON p.sesion_id = s.id JOIN trabajo t ON p.trabajo_id = t.id JOIN congresista c ON p.ponente_id = c.id; SELECT p.id AS presentacion_id, t.titulo AS trabajo_titulo, c.nombre AS ponente, s.sala, s.fecha_hora FROM presentacion p JOIN trabajo t ON p.trabajo_id = t.id JOIN congresista c ON p.ponente_id = c.id JOIN sesion s ON p.sesion_id = s.id; SELECT t.id AS trabajo_id, t.titulo, c.nombre AS ponente FROM trabajo t JOIN presentacion p ON t.id = p.trabajo_id JOIN congresista c ON p.ponente_id = c.id;"






docker exec -it postgres-congreso psql -U congresoadmin -d gestion_congreso -c "SELECT * FROM congresista; SELECT * FROM trabajo; SELECT * FROM autor_trabajo; SELECT t.id AS trabajo_id, t.titulo, t.resumen, c.id AS congresista_id, c.nombre, c.apellido FROM trabajo t JOIN autor_trabajo at ON t.id = at.trabajo_id JOIN congresista c ON at.congresista_id = c.id; SELECT * FROM sesion; SELECT p.id AS presentacion_id, s.sala, s.fecha_hora, t.titulo AS trabajo_titulo, c.nombre AS ponente FROM presentacion p JOIN sesion s ON p.sesion_id = s.id JOIN trabajo t ON p.trabajo_id = t.id JOIN congresista c ON p.ponente_id = c.id; SELECT p.id AS presentacion_id, t.titulo AS trabajo_titulo, c.nombre AS ponente, s.sala, s.fecha_hora FROM presentacion p JOIN trabajo t ON p.trabajo_id = t.id JOIN congresista c ON p.ponente_id = c.id JOIN sesion s ON p.sesion_id = s.id; SELECT t.id AS trabajo_id, t.titulo, c.nombre AS ponente FROM trabajo t JOIN presentacion p ON t.id = p.trabajo_id JOIN congresista c ON p.ponente_id = c.id;"
 id | apellido | correo_electronico |      institucion      | nombre | telefono_movil
----+----------+--------------------+-----------------------+--------+----------------
  1 | Pérez    | juan@example.com   | Universidad Nacional  | Juan   | 123456789
  2 | Gómez    | ana@example.com    | Instituto Tecnológico | Ana    | 987654321
  3 | López    | carlos@example.com | Universidad Central   | Carlos | 456789123
(3 rows)

 id |                            resumen                             |               titulo
----+----------------------------------------------------------------+-------------------------------------
  1 | Un estudio sobre el impacto de la IA en diagnósticos médicos.  | Inteligencia Artificial en Medicina
  2 | Análisis del uso de blockchain para mejorar la ciberseguridad. | Blockchain y Seguridad
  3 | Investigación sobre el futuro de las energías limpias.         | Energías Renovables
(3 rows)

 id | congresista_id | trabajo_id
----+----------------+------------
  1 |              1 |          1
  2 |              2 |          2
  3 |              3 |          3
(3 rows)

 trabajo_id |               titulo                |                            resumen                             | congresista_id | nombre | apellido
------------+-------------------------------------+----------------------------------------------------------------+----------------+--------+----------
          1 | Inteligencia Artificial en Medicina | Un estudio sobre el impacto de la IA en diagnósticos médicos.  |              1 | Juan   | Pérez
          2 | Blockchain y Seguridad              | Análisis del uso de blockchain para mejorar la ciberseguridad. |              2 | Ana    | Gómez
          3 | Energías Renovables                 | Investigación sobre el futuro de las energías limpias.         |              3 | Carlos | López
(3 rows)

 id |     fecha_hora      |        sala         | chairman_id
----+---------------------+---------------------+-------------
  1 | 2025-03-10 09:00:00 | Aula Magna          |           1
  2 | 2025-03-10 11:00:00 | Salón Azul          |           2
  3 | 2025-03-10 14:00:00 | Auditorio Principal |           3
(3 rows)

 presentacion_id |        sala         |     fecha_hora      |           trabajo_titulo            | ponente
-----------------+---------------------+---------------------+-------------------------------------+---------
               1 | Aula Magna          | 2025-03-10 09:00:00 | Inteligencia Artificial en Medicina | Juan
               2 | Salón Azul          | 2025-03-10 11:00:00 | Blockchain y Seguridad              | Ana
               3 | Auditorio Principal | 2025-03-10 14:00:00 | Energías Renovables                 | Carlos
(3 rows)

 presentacion_id |           trabajo_titulo            | ponente |        sala         |     fecha_hora
-----------------+-------------------------------------+---------+---------------------+---------------------
               1 | Inteligencia Artificial en Medicina | Juan    | Aula Magna          | 2025-03-10 09:00:00
               2 | Blockchain y Seguridad              | Ana     | Salón Azul          | 2025-03-10 11:00:00
               3 | Energías Renovables                 | Carlos  | Auditorio Principal | 2025-03-10 14:00:00
(3 rows)

 trabajo_id |               titulo                | ponente
------------+-------------------------------------+---------
          1 | Inteligencia Artificial en Medicina | Juan
          2 | Blockchain y Seguridad              | Ana
          3 | Energías Renovables                 | Carlos
(3 rows)

-- Insertar 3 congresistas
INSERT INTO congresista (nombre, apellido, institucion, correo_electronico, telefono_movil)
VALUES 
    ('Juan', 'Pérez', 'Universidad Nacional', 'juan@example.com', '123456789'),
    ('Ana', 'Gómez', 'Instituto Tecnológico', 'ana@example.com', '987654321'),
    ('Carlos', 'López', 'Universidad Central', 'carlos@example.com', '456789123');

-- Insertar 3 trabajos científicos
INSERT INTO trabajo (titulo, resumen)
VALUES 
    ('Inteligencia Artificial en Medicina', 'Un estudio sobre el impacto de la IA en diagnósticos médicos.'),
    ('Blockchain y Seguridad', 'Análisis del uso de blockchain para mejorar la ciberseguridad.'),
    ('Energías Renovables', 'Investigación sobre el futuro de las energías limpias.');

-- Relacionar trabajos con autores (tabla intermedia autor_trabajo)
INSERT INTO autor_trabajo (trabajo_id, congresista_id)
VALUES 
    (1, 1),  -- Juan es autor del trabajo 1
    (2, 2),  -- Ana es autora del trabajo 2
    (3, 3);  -- Carlos es autor del trabajo 3

-- Insertar 3 sesiones del congreso
INSERT INTO sesion (sala, fecha_hora, chairman_id)
VALUES 
    ('Aula Magna', '2025-03-10 09:00:00', 1),
    ('Salón Azul', '2025-03-10 11:00:00', 2),
    ('Auditorio Principal', '2025-03-10 14:00:00', 3);

-- Insertar 3 presentaciones en sesiones
INSERT INTO presentacion (trabajo_id, sesion_id, ponente_id)
VALUES 
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 3);

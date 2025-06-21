-- Limpiar tablas (por orden para evitar conflictos FK)
DELETE FROM calificaciones;
DELETE FROM alumno_modulo;
DELETE FROM expedientes;
DELETE FROM modulos;
DELETE FROM alumnos;

-- Insertar módulos
INSERT INTO modulos (nombre, siglas, horas) VALUES
    ('Programación', 'PROG', 120),
    ('Bases de Datos', 'BBDD', 80),
    ('Sistemas Informaticos', 'SI', 80),
    ('Lenguaje de Marcas', 'LMM', 60),
    ('Itinerario Para la Empleabilidad', 'IPE', 60),
    ('Entornos de Desarrollo', 'EED', 40),
    ('Optimizacion y posicionamiento SEO', 'SEO', 40);


-- Insertar alumnos
INSERT INTO alumnos (nombre, apellidos, fecha_nacimiento, edad, nacionalidad, fecha_incorporacion, nota_media, faltas, retrasos, partes) VALUES
    ('Ana', 'García', '2003-05-10', 21, 'Española', '2022-09-01', 7.5, 2, 1, 0),
    ('Luis', 'Martínez', '2002-03-15', 22, 'Española', '2022-09-01', 8.3, 0, 0, 1),
    ('María', 'López', '2003-07-20', 21, 'Española', '2022-09-01', 6.9, 3, 2, 0),
    ('Carlos', 'Fernández', '2001-12-05', 23, 'Española', '2022-09-01', 9.1, 0, 1, 0),
    ('Lucía', 'Sánchez', '2004-01-30', 20, 'Española', '2022-09-01', 7.8, 1, 0, 0),
    ('Javier', 'Ruiz', '2003-04-17', 21, 'Española', '2022-09-01', 8.0, 0, 0, 0),
    ('Elena', 'Gómez', '2002-08-12', 22, 'Española', '2022-09-01', 7.2, 2, 1, 0),
    ('Pablo', 'Díaz', '2003-11-08', 21, 'Española', '2022-09-01', 6.5, 4, 2, 1),
    ('Sofía', 'Morales', '2004-06-25', 20, 'Española', '2022-09-01', 8.7, 1, 0, 0),
    ('Raúl', 'Jiménez', '2002-09-14', 22, 'Española', '2022-09-01', 7.9, 0, 0, 0);

-- Insertar expedientes
INSERT INTO expedientes (id_alumno, nota_media, observaciones) VALUES
    (1, 7.5, 'Buen rendimiento'),
    (2, 8.3, 'Muy aplicado'),
    (3, 6.9, 'Puede mejorar'),
    (4, 9.1, 'Excelente alumno'),
    (5, 7.8, ''),
    (6, 8.0, 'Constante'),
    (7, 7.2, 'Necesita apoyo'),
    (8, 6.5, 'Asistencia baja'),
    (9, 8.7, 'Muy buena actitud'),
    (10, 7.9, 'Cumple expectativas');

-- Insertar calificaciones para cada expediente
INSERT INTO calificaciones (id_expediente, modulo_siglas, nota) VALUES
    -- Expediente 1
    (1, 'PROG', 7.5),
    (1, 'BBDD', 8.0),
    (1, 'SI', 6.5),
    (1, 'LMM', 7.0),
    (1, 'IPE', 8.0),
    (1, 'EED', 7.0),
    (1, 'SEO', 9.0),

    -- Expediente 2
    (2, 'PROG', 8.5),
    (2, 'BBDD', 7.0),
    (2, 'SI', 7.5),
    (2, 'LMM', 6.0),
    (2, 'IPE', 7.0),
    (2, 'EED', 8.0),
    (2, 'SEO', 8.5),

    -- Expediente 3
    (3, 'PROG', 6.0),
    (3, 'BBDD', 7.5),
    (3, 'SI', 8.0),
    (3, 'LMM', 7.0),
    (3, 'IPE', 6.5),
    (3, 'EED', 7.0),
    (3, 'SEO', 7.5),

    -- Expediente 4
    (4, 'PROG', 9.5),
    (4, 'BBDD', 9.0),
    (4, 'SI', 8.0),
    (4, 'LMM', 7.5),
    (4, 'IPE', 8.0),
    (4, 'EED', 9.0),
    (4, 'SEO', 9.5),

    -- Expediente 5
    (5, 'PROG', 7.0),
    (5, 'BBDD', 7.5),
    (5, 'SI', 7.0),
    (5, 'LMM', 8.0),
    (5, 'IPE', 7.5),
    (5, 'EED', 6.5),
    (5, 'SEO', 8.0),

    -- Expediente 6
    (6, 'PROG', 8.0),
    (6, 'BBDD', 6.0),
    (6, 'SI', 7.0),
    (6, 'LMM', 7.5),
    (6, 'IPE', 8.0),
    (6, 'EED', 7.0),
    (6, 'SEO', 6.5),

    -- Expediente 7
    (7, 'PROG', 7.0),
    (7, 'BBDD', 8.0),
    (7, 'SI', 6.0),
    (7, 'LMM', 7.0),
    (7, 'IPE', 7.5),
    (7, 'EED', 8.0),
    (7, 'SEO', 7.0),

    -- Expediente 8
    (8, 'PROG', 6.0),
    (8, 'BBDD', 6.5),
    (8, 'SI', 7.0),
    (8, 'LMM', 8.0),
    (8, 'IPE', 7.0),
    (8, 'EED', 7.5),
    (8, 'SEO', 8.0),

    -- Expediente 9
    (9, 'PROG', 9.0),
    (9, 'BBDD', 8.5),
    (9, 'SI', 8.0),
    (9, 'LMM', 7.0),
    (9, 'IPE', 7.5),
    (9, 'EED', 8.0),
    (9, 'SEO', 8.5),

    -- Expediente 10
    (10, 'PROG', 7.8),
    (10, 'BBDD', 7.0),
    (10, 'SI', 7.5),
    (10, 'LMM', 6.5),
    (10, 'IPE', 7.0),
    (10, 'EED', 7.5),
    (10, 'SEO', 8.0);


-- Relacionar cada alumno con TODOS los módulos
INSERT INTO alumno_modulo (id_alumno, id_modulo) VALUES

    -- Alumno 1 con todos los módulos
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7),
    -- Alumno 2 con todos los módulos
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7),
    -- Alumno 3 con todos los módulos
    (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7),
    -- Alumno 4 con todos los módulos
    (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7),
    -- Alumno 5 con todos los módulos
    (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7),
    -- Alumno 6 con todos los módulos
    (6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7),
    -- Alumno 7 con todos los módulos
    (7, 1), (7, 2), (7, 3), (7, 4), (7, 5), (7, 6), (7, 7),
    -- Alumno 8 con todos los módulos
    (8, 1), (8, 2), (8, 3), (8, 4), (8, 5), (8, 6), (8, 7),
    -- Alumno 9 con todos los módulos
    (9, 1), (9, 2), (9, 3), (9, 4), (9, 5), (9, 6), (9, 7),
    -- Alumno 10 con todos los módulos
    (10, 1), (10, 2), (10, 3), (10, 4), (10, 5), (10, 6), (10, 7);

-- Insertar datos en la tabla Asignatura
INSERT INTO `asignatura` (nombre, codigo) VALUES ('Cálculo II', '311-1');
INSERT INTO `asignatura` (nombre, codigo) VALUES ('Estructuras de Datos I', 'SIS301');
INSERT INTO `asignatura` (nombre, codigo) VALUES ('Análisis Numérico', '328-1');
INSERT INTO `asignatura` (nombre, codigo) VALUES ('Ingeniería de Software II', 'SIS602');

-- Insertar datos en la tabla Curso
INSERT INTO `curso` (asignatura_id, nombre) VALUES (1, 'Cálculo II - Grupo A');
INSERT INTO `curso` (asignatura_id, nombre) VALUES (1, 'Cálculo II - Grupo B');
INSERT INTO `curso` (asignatura_id, nombre) VALUES (2, 'Estructuras de Datos I - Grupo A');
INSERT INTO `curso` (asignatura_id, nombre) VALUES (2, 'Estructuras de Datos I - Grupo B');
INSERT INTO `curso` (asignatura_id, nombre) VALUES (3, 'Análisis Numérico - Grupo A');
INSERT INTO `curso` (asignatura_id, nombre) VALUES (4, 'Ingeniería de Software II - Grupo A');

-- Insertar datos en la tabla EspacioFisico
INSERT INTO `espacio_fisico` (capacidad, nombre) VALUES (300, 'Auditorio Principal FIET');
INSERT INTO `espacio_fisico` (capacidad, nombre) VALUES (18, 'Sala 4 FIET');
INSERT INTO `espacio_fisico` (capacidad, nombre) VALUES (20, 'Salon 333 FIET');
INSERT INTO `espacio_fisico` (capacidad, nombre) VALUES (28, 'Salon 221 FIET');

-- Insertar datos en la tabla Persona
INSERT INTO `persona` (apellido_persona, correo_persona, nombre_persona) VALUES ('Gómez', 'gomez@unicauca.edu.co', 'Juan');
INSERT INTO `persona` (apellido_persona, correo_persona, nombre_persona) VALUES ('Pérez', 'perez@unicauca.edu.co', 'María');
INSERT INTO `persona` (apellido_persona, correo_persona, nombre_persona) VALUES ('Rodríguez', 'rodriguez@unicauca.edu.co', 'Luis');
INSERT INTO `persona` (apellido_persona, correo_persona, nombre_persona) VALUES ('López', 'lopez@unicauca.edu.co', 'Ana');

-- Insertar datos en la tabla Administrativo
INSERT INTO `administrativo` (persona_id, rol) VALUES (1, 'Administrador del Sistema');
INSERT INTO `administrativo` (persona_id, rol) VALUES (3, 'Coordinador de Programas de Sistemas');

-- Insertar datos en la tabla Oficina
INSERT INTO `oficina` (nombre, ubicacion) VALUES ('Oficina 404', 'Cuarto Piso FIET');
INSERT INTO `oficina` (nombre, ubicacion) VALUES ('Oficina 110', 'Postgrados FIET');
INSERT INTO `oficina` (nombre, ubicacion) VALUES ('Oficina 412', 'Cuarto Piso FIET');
INSERT INTO `oficina` (nombre, ubicacion) VALUES ('Oficina 15', 'Edificio TICs');

-- Inserción de tres docentes en la tabla Docente
INSERT INTO `docente` (oficina_id, persona_id) VALUES (3, 3);
INSERT INTO `docente` (oficina_id, persona_id) VALUES (2, 2);
INSERT INTO `docente` (oficina_id, persona_id) VALUES (1, 4);

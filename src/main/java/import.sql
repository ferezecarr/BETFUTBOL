-- SOLO SE TIENE ESTO EN CUENTA SI EN EL 'hibernateContext.xml' esta seteado el 'create-drop'
-- Si se crease una tabla o algo de lo que no se encarga hibernate, hay que dropearlo tambien

-- ============================================================================================

-- Creando un usuario
INSERT INTO Usuario (nombreYApellido, email, password) VALUES ('test','test@test.com','test')

-- Creando diez equipos
INSERT INTO Equipo (nombre) VALUES ("Argentina");
INSERT INTO Equipo (nombre) VALUES ("China");
INSERT INTO Equipo (nombre) VALUES ("Inglaterra");
INSERT INTO Equipo (nombre) VALUES ("Corea del sur");
INSERT INTO Equipo (nombre) VALUES ("Belgica");
INSERT INTO Equipo (nombre) VALUES ("Uruguay");
INSERT INTO Equipo (nombre) VALUES ("Nigeria");
INSERT INTO Equipo (nombre) VALUES ("Alemania");
INSERT INTO Equipo (nombre) VALUES ("Jamaica");
INSERT INTO Equipo (nombre) VALUES ("Brasil");

-- Creando cinco partidos
INSERT INTO Partido (id_equipo_local, id_equipo_visitante, fecha) VALUES (1,2,'2017-11-05 13:00:00');
INSERT INTO Partido (id_equipo_local, id_equipo_visitante, fecha) VALUES (3,4,'2017-11-05 13:00:00');
INSERT INTO Partido (id_equipo_local, id_equipo_visitante, fecha) VALUES (5,6,'2017-11-05 16:00:00');
INSERT INTO Partido (id_equipo_local, id_equipo_visitante, fecha) VALUES (7,8,'2017-11-05 16:00:00');
INSERT INTO Partido (id_equipo_local, id_equipo_visitante, fecha) VALUES (9,10,'2017-11-05 18:30:00');

-- Creando cinco eventos
INSERT INTO Evento (id_partido, nombre) VALUES (1, "Resultado");
INSERT INTO Evento (id_partido, nombre) VALUES (2, "Resultado");
INSERT INTO Evento (id_partido, nombre) VALUES (3, "Resultado");
INSERT INTO Evento (id_partido, nombre) VALUES (4, "Resultado");
INSERT INTO Evento (id_partido, nombre) VALUES (5, "Resultado");

-- Creando muchas cuotas
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (1, "Gana Argentina", 1.44);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (1, "Empate", 2.32);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (1, "Gana China", 4.90);

INSERT INTO Cuota (evento_id, nombre, valor) VALUES (2, "Gana Inglaterra", 1.89);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (2, "Empate", 2.5);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (2, "Gana Corea del Sur", 2.5);

INSERT INTO Cuota (evento_id, nombre, valor) VALUES (3, "Gana Belgica", 2.01);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (3, "Empate", 2.93);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (3, "Gana Uruguay", 2.44);

INSERT INTO Cuota (evento_id, nombre, valor) VALUES (4, "Gana Nigeria", 2.51);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (4, "Empate", 2.28);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (4, "Gana Alemania", 1.94);

INSERT INTO Cuota (evento_id, nombre, valor) VALUES (5, "Gana Jamaica", 3.01);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (5, "Empate", 2.23);
INSERT INTO Cuota (evento_id, nombre, valor) VALUES (5, "Gana Brasil", 1.67);


-- SOLO SE TIENE ESTO EN CUENTA SI EN EL 'hibernateContext.xml' esta seteado el 'create-drop'
-- Si se crease una tabla o algo de lo que no se encarga hibernate, hay que dropearlo tambien

-- ============================================================================================

-- Creando usuarios
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (1,'test','test@test.com','test', 'ADMIN');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (2,'admin','admin@betfutbol.com','admin', 'ADMIN');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (3,'Fernando Carreño','ferezecarr@gmail.com','12345', 'USER');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (4,'Mauro Ledesma','mauro@betfutbol.com','mauro', 'USER');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (5,'Ignacio Castiñeira','ignacio@betfutbol.com','ignacio', 'USER');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (6,'Ariel Rivero','ariel@betfutbol.com','ariel', 'USER');
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES (7,'Jonatan Gimenez','jonatan@betfutbol.com','jonatan', 'USER');

-- Creando doce equipos
INSERT INTO Equipo (id, nombre) VALUES (1, "Argentina");
INSERT INTO Equipo (id, nombre) VALUES (2, "China");
INSERT INTO Equipo (id, nombre) VALUES (3, "Napoli");
INSERT INTO Equipo (id, nombre) VALUES (4, "Juventus");
INSERT INTO Equipo (id, nombre) VALUES (5, "Barcelona");
INSERT INTO Equipo (id, nombre) VALUES (6, "Celta de Vigo");
INSERT INTO Equipo (id, nombre) VALUES (7, "Racing de Estraburgo");
INSERT INTO Equipo (id, nombre) VALUES (8, "PSG");
INSERT INTO Equipo (id, nombre) VALUES (9, "Independiente");
INSERT INTO Equipo (id, nombre) VALUES (10, "Rosario Central");
INSERT INTO Equipo (id, nombre) VALUES (11, "Gimnasia LP");
INSERT INTO Equipo (id, nombre) VALUES (12, "River Plate");

-- Creando siete partidos
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (1,2,1,'2017-11-01 15:30:00', 0, 4, TRUE, TRUE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (2,1,2,'2017-11-15 13:00:00', 0, 0, TRUE, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (3,3,4,'2017-12-01 16:45:00', 0, 0, FALSE, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (4,5,6,'2017-12-02 09:00:00', 0, 0, FALSE, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (5,7,8,'2017-12-02 13:00:00', 0, 0, FALSE, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (6,9,10,'2017-12-02 19:15:00', 0, 0, FALSE, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES (7,11,12,'2017-12-03 21:30:00', 0, 0, FALSE, FALSE);

-- Creando siete eventos de tipo "Resultado" (Gana, empate, derrota)
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado, cuotaGanadora) VALUES (1, 1, "Resultado", "| China Vs Argentina | 01/11 - 15:30hs", TRUE, "Gana Argentina");
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (2, 2, "Resultado", "| Argentina Vs China | 29/11 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (3, 3, "Resultado", "| Serie A | 01/12 - 16:45hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (4, 4, "Resultado", "| Liga BBVA | 02/12 - 09:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (5, 5, "Resultado", "| Ligue One | 02/12 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (6, 6, "Resultado", "| Primera División Argentina | 02/12 - 19:15hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (7, 7, "Resultado", "| Primera División Argentina | 03/12 - 21:30hs", FALSE);

-- Creando cuatro eventos de tipo "Equipo hace goles" (ninguno, uno, dos, mas de dos)
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (8, 5, "Cuantos goles hace un equipo", "| Ligue One | Goles de Racing de Estraburgo ante PSG | 02/12 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (9, 5, "Cuantos goles hace un equipo", "| Ligue One | Goles de PSG ante Racing de Estraburgo | 02/12 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (10, 6, "Cuantos goles hace un equipo", "| Primera División Argentina | Goles de Independiente ante Rosario Central | 02/12 - 19:15hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (11, 6, "Cuantos goles hace un equipo", "| Primera División Argentina | Goles de Rosario Central ante Independiente | 02/12 - 19:15hs", FALSE);

-- Creando cuotas que son asignadas a los eventos de tipo "Resultado"
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (1, 1, "Gana China", 4.90, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (2, 1, "Empate", 2.32, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (3, 1, "Gana Argentina", 1.44, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (4, 2, "Gana Argentina", 1.44, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (5, 2, "Empate", 2.32, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (6, 2, "Gana China", 4.90, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (7, 3, "Gana Napoli", 1.89, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (8, 3, "Empate", 2.08, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (9, 3, "Gana Juventus", 2.5, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (10, 4, "Gana Barcelona", 1.55, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (11, 4, "Empate", 2.93, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (12, 4, "Gana Celta de Vigo", 3.78, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (13, 5, "Gana Racing de Estraburgo", 5.03, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (14, 5, "Empate", 2.76, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (15, 5, "Gana PSG", 1.48, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (16, 6, "Gana Independiente", 1.79, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (17, 6, "Empate", 2.36, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (18, 6, "Gana Rosario Central", 2.43, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (19, 7, "Gana Gimnasia LP", 2.06, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (20, 7, "Empate", 1.64, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (21, 7, "Gana River Plate", 1.87, 0);

-- Creando cuotas que son asignadas a los eventos de tipo "Equipo hace goles"
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (22, 8, "0", 1.43, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (23, 8, "1", 1.99, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (24, 8, "2", 2.50, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (25, 8, "+2", 3.01, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (26, 9, "0", 3.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (27, 9, "1", 2.50, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (28, 9, "2", 1.99, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (29, 9, "+2", 1.43, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (30, 10, "0", 2.00, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (31, 10, "1", 1.90, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (32, 10, "2", 2.13, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (33, 10, "+2", 2.26, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (34, 11, "0", 1.70, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (35, 11, "1", 2.00, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (36, 11, "2", 2.65, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (37, 11, "+2", 3.43, 0);

-- Hardcodeando apuestas
INSERT INTO Apuesta(id, evento_id, apostador_id, cantidadApostada, cuotaValor, cuotaNombre, isGanadora) VALUES (1, 1, 3, 20.50, 1.44, "Gana Argentina", TRUE);
INSERT INTO Apuesta(id, evento_id, apostador_id, cantidadApostada, cuotaValor, cuotaNombre, isGanadora) VALUES (2, 2, 1, 100.00, 1.44, "Gana Argentina", FALSE);

-- Evento que setea los partidos finalizados (hay que dropear, no lo maneja Hibernate)
DROP EVENT IF EXISTS TERMINAR_PARTIDO;
SET GLOBAL event_scheduler = ON;
CREATE EVENT TERMINAR_PARTIDO ON SCHEDULE EVERY 1 MINUTE DO UPDATE Partido P SET P.isTerminado=TRUE WHERE P.isTerminado=FALSE AND TIMESTAMPDIFF(MINUTE, P.fecha, NOW()) >= 120;

-- Trigger que setea las apuestas que tienen premio (hay que dropear, no lo maneja Hibernate)
DROP TRIGGER IF EXISTS PREMIAR_APUESTAS;
CREATE TRIGGER PREMIAR_APUESTAS AFTER UPDATE ON EVENTO FOR EACH ROW BEGIN IF(NEW.isTerminado IS TRUE) THEN UPDATE Apuesta A JOIN Evento E ON A.evento_id=E.id SET A.isGanadora=TRUE WHERE A.isGanadora=FALSE AND A.cuotaNombre=E.cuotaGanadora; END IF; END; 
-- SOLO SE TIENE ESTO EN CUENTA SI EN EL 'hibernateContext.xml' esta seteado el 'create-drop'
-- Si se crease una tabla o algo de lo que no se encarga hibernate, hay que dropearlo tambien

-- ============================================================================================

-- Creando un usuario
INSERT INTO Usuario (id, nombreYApellido, email, password) VALUES (1,'test','test@test.com','test');
INSERT INTO Usuario (id, nombreYApellido, email, password) VALUES (2,'Fernando Carre�o','ferezecarr@gmail.com','12345');
-- Creando diez equipos
INSERT INTO Equipo (id, nombre) VALUES (1, "Argentina");
INSERT INTO Equipo (id, nombre) VALUES (2, "China");
INSERT INTO Equipo (id, nombre) VALUES (3, "Inglaterra");
INSERT INTO Equipo (id, nombre) VALUES (4, "Corea del sur");
INSERT INTO Equipo (id, nombre) VALUES (5, "Belgica");
INSERT INTO Equipo (id, nombre) VALUES (6, "Uruguay");
INSERT INTO Equipo (id, nombre) VALUES (7, "Nigeria");
INSERT INTO Equipo (id, nombre) VALUES (8, "Alemania");
INSERT INTO Equipo (id, nombre) VALUES (9, "Jamaica");
INSERT INTO Equipo (id, nombre) VALUES (10, "Brasil");

-- Creando seis partidos
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (1,2,1,'2017-11-01 15:30:00', 0, 4, TRUE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (2,1,2,'2017-11-05 13:00:00', 0, 0, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (3,3,4,'2017-11-18 13:00:00', 0, 0, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (4,5,6,'2017-11-19 16:00:00', 0, 0, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (5,7,8,'2017-11-20 16:00:00', 0, 0, FALSE);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isResultadoFinal) VALUES (6,9,10,'2017-11-21 18:30:00', 0, 0, FALSE);

-- Creando seis eventos de tipo "Resultado" (Gana, empate, derrota)
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (1, 1, "Resultado", "| Amistoso internacional | 01/11 - 13:00hs", TRUE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (2, 2, "Resultado", "| Amistoso internacional | 05/11 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (3, 3, "Resultado", "| Amistoso internacional | 18/11 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (4, 4, "Resultado", "| Amistoso internacional | 19/11 - 16:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (5, 5, "Resultado", "| Amistoso internacional | 20/11 - 16:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (6, 6, "Resultado", "| Amistoso internacional | 21/11 - 18:30hs", FALSE);

-- Creando cuatro eventos de tipo "Equipo hace goles" (ninguno, uno, dos, mas de dos)
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (7, 5, "Cuantos goles hace un equipo", "| Amistoso internacional - Goles de Nigeria ante Alemania | 21/11 - 18:30hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (8, 5, "Cuantos goles hace un equipo", "| Amistoso internacional - Goles de Alemania ante Nigeria | 21/11 - 18:30hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (9, 6, "Cuantos goles hace un equipo", "| Amistoso internacional - Goles de Jamaica ante Brasil | 21/11 - 18:30hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (10, 6, "Cuantos goles hace un equipo", "| Amistoso internacional - Goles de Brasil ante Jamaica | 21/11 - 18:30hs", FALSE);

-- Creando cuotas que son asignadas a los eventos de tipo "Resultado"
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (1, 1, "Gana China", 4.90, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (2, 1, "Empate", 2.32, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (3, 1, "Gana Argentina", 1.44, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (4, 2, "Gana Argentina", 1.44, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (5, 2, "Empate", 2.32, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (6, 2, "Gana China", 4.90, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (7, 3, "Gana Inglaterra", 1.89, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (8, 3, "Empate", 2.5, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (9, 3, "Gana Corea del Sur", 2.5, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (10, 4, "Gana Belgica", 2.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (11, 4, "Empate", 2.93, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (12, 4, "Gana Uruguay", 2.44, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (13, 5, "Gana Nigeria", 2.51, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (14, 5, "Empate", 2.28, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (15, 5, "Gana Alemania", 1.94, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (16, 6, "Gana Jamaica", 3.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (17, 6, "Empate", 2.23, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (18, 6, "Gana Brasil", 1.67, 0);

-- Creando cuotas que son asignadas a los eventos de tipo "Equipo hace goles"
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (19, 7, "0", 1.43, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (20, 7, "1", 1.99, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (21, 7, "2", 2.50, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (22, 7, "+2", 3.01, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (23, 8, "0", 3.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (24, 8, "1", 2.50, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (25, 8, "2", 1.99, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (26, 8, "+2", 1.43, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (27, 9, "0", 1.38, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (28, 9, "1", 1.77, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (29, 9, "2", 2.63, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (30, 9, "+2", 5.08, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (31, 10, "0", 5.08, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (32, 10, "1", 2.63, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (33, 10, "2", 1.77, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (34, 10, "+2", 1.38, 0);

-- Evento que setea los partidos finalizados (hay que dropear, No lo maneja hibernate)
DROP EVENT IF EXISTS TERMINAR_PARTIDO;
SET GLOBAL event_scheduler = ON;
CREATE EVENT TERMINAR_PARTIDO ON SCHEDULE EVERY 1 MINUTE DO UPDATE Evento E SET E.isTerminado=TRUE WHERE E.isTerminado=FALSE AND EXISTS(SELECT P.id FROM Partido P WHERE P.id=E.id_partido AND TIMESTAMPDIFF(MINUTE, P.fecha, NOW()) >= 120);
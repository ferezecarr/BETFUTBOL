-- SOLO SE TIENE ESTO EN CUENTA SI EN EL 'hibernateContext.xml' esta seteado el 'create-drop'
-- Si se crease una tabla o algo de lo que no se encarga hibernate, hay que dropearlo tambien

-- ============================================================================================

-- Creando un usuario
INSERT INTO Usuario (id, nombreYApellido, email, password) VALUES (1,'test','test@test.com','test')

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

-- Creando cinco partidos
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante) VALUES (1,1,2,'2017-11-05 13:00:00', 0, 0);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante) VALUES (2,3,4,'2017-11-11 13:00:00', 0, 0);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante) VALUES (3,5,6,'2017-11-12 16:00:00', 0, 0);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante) VALUES (4,7,8,'2017-11-13 16:00:00', 0, 0);
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante) VALUES (5,9,10,'2017-11-14 18:30:00', 0, 0);

-- Creando cinco eventos
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (1, 1, "Resultado", "| Amistoso internacional | 05/11 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (2, 2, "Resultado", "| Amistoso internacional | 11/11 - 13:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (3, 3, "Resultado", "| Amistoso internacional | 12/11 - 16:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (4, 4, "Resultado", "| Amistoso internacional | 13/11 - 16:00hs", FALSE);
INSERT INTO Evento (id, id_partido, nombre, descripcion, isTerminado) VALUES (5, 5, "Resultado", "| Amistoso internacional | 14/11 - 18:30hs", FALSE);

-- Creando muchas cuotas
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (1, 1, "Gana Argentina", 1.44, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (2, 1, "Empate", 2.32, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (3, 1, "Gana China", 4.90, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (4, 2, "Gana Inglaterra", 1.89, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (5, 2, "Empate", 2.5, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (6, 2, "Gana Corea del Sur", 2.5, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (7, 3, "Gana Belgica", 2.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (8, 3, "Empate", 2.93, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (9, 3, "Gana Uruguay", 2.44, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (10, 4, "Gana Nigeria", 2.51, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (11, 4, "Empate", 2.28, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (12, 4, "Gana Alemania", 1.94, 0);

INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (13, 5, "Gana Jamaica", 3.01, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (14, 5, "Empate", 2.23, 0);
INSERT INTO Cuota (id, evento_id, nombre, valor, cantidadVotos) VALUES (15, 5, "Gana Brasil", 1.67, 0);

-- Evento que setea los partidos finalizados (hay que dropear, No lo maneja hibernate)
DROP EVENT IF EXISTS TERMINAR_PARTIDO;
SET GLOBAL event_scheduler = ON;
CREATE EVENT TERMINAR_PARTIDO ON SCHEDULE EVERY 1 MINUTE DO UPDATE Evento E SET E.isTerminado=TRUE WHERE E.isTerminado=FALSE AND EXISTS(SELECT P.id FROM Partido P WHERE P.id=E.id_partido AND TIMESTAMPDIFF(SECOND, P.fecha, NOW()) >= 0);
-- SOLO SE TIENE ESTO EN CUENTA SI EN EL 'hibernateContext.xml' esta seteado el 'create-drop'
-- Si se crease una tabla o algo de lo que no se encarga hibernate, hay que dropearlo tambien

-- ============================================================================================

-- Creando usuarios
INSERT INTO Usuario (id, nombreYApellido, email, password, rol) VALUES
					(1,'test','test@test.com','test', 'ADMIN'),
					(2,'admin','admin@betfutbol.com','admin', 'ADMIN'),
					(3,'Fernando Carreño','ferezecarr@gmail.com','12345', 'USER'),
					(4,'Mauro Ledesma','mauro@betfutbol.com','mauro', 'USER'),
					(5,'Ignacio Castiñeira','ignacio@betfutbol.com','ignacio', 'USER'),
					(6,'Ariel Rivero','ariel@betfutbol.com','ariel', 'USER'),
					(7,'Jonatan Gimenez','jonatan@betfutbol.com','jonatan', 'USER');

-- Creando doce equipos
INSERT INTO Equipo 	(id, nombre) VALUES 
					(1, "Argentina"),
					(2, "China"),
					(3, "Napoli"),
					(4, "Juventus"),
 					(5, "Barcelona"),
					(6, "Celta de Vigo"),
 					(7, "Racing de Estraburgo"),
					(8, "PSG"),
 					(9, "Independiente"),
					(10, "Rosario Central"),
 					(11, "Gimnasia LP"),
 					(12, "River Plate");

-- Creando siete partidos
INSERT INTO Partido (id, id_equipo_local, id_equipo_visitante, fecha, golesLocal, golesVisitante, isTerminado, isResultadoFinal) VALUES 
					(1,2,1,'2017-11-01 15:30:00', 0, 4, TRUE, TRUE),
					(2,1,2,'2017-11-15 13:00:00', 0, 0, TRUE, FALSE),
 					(3,3,4,'2017-12-01 16:45:00', 0, 0, FALSE, FALSE),
 					(4,5,6,'2017-12-02 09:00:00', 0, 0, FALSE, FALSE),
 					(5,7,8,'2017-12-02 13:00:00', 0, 0, FALSE, FALSE),
 					(6,9,10,'2017-12-02 19:15:00', 0, 0, FALSE, FALSE),
 					(7,11,12,'2017-12-03 21:30:00', 0, 0, FALSE, FALSE);

-- Creando siete eventos de tipo "Resultado" (Gana, empate, derrota)
INSERT INTO Evento 	(id, id_partido, nombre, descripcion, isTerminado, cuotaGanadora) VALUES 
					(1, 1, "Resultado", "[Resultado] (L) China Vs Argentina (V) |Amistoso Internacional| - 01/11 15:30Hs", TRUE, "Gana Argentina"),
					(2, 2, "Resultado", "[Resultado] (L) Argentina Vs China (V) |Amistoso Internacional| - 29/11 13:00Hs", FALSE, NULL),
 					(3, 3, "Resultado", "[Resultado] (L) Napoli Vs Juventus (V) |Serie A| - 01/12 16:45Hs", FALSE, NULL),
 					(4, 4, "Resultado", "[Resultado] (L) Barcelona Vs Celta de Vigo (V) |Liga BBVA| - 02/12 09:00Hs", FALSE, NULL),
 					(5, 5, "Resultado", "[Resultado] (L) Racing de Estraburgo Vs PSG (V) |Ligue One| - 02/12 13:00Hs", FALSE, NULL),
 					(6, 6, "Resultado", "[Resultado] (L) Independiente Vs Rosario Central (V) |Primera División Argentina| - 02/12 19:15Hs", FALSE, NULL),
 					(7, 7, "Resultado", "[Resultado] (L) Gimnasia LP Vs River Plate (V) |Primera División Argentina| - 03/12 21:30Hs", FALSE, NULL);

-- Creando un evento de tipo "Cantidad de goles en un partido" (0, 1, 2, 3, 4, +4)
INSERT INTO Evento 	(id, id_partido, nombre, descripcion, isTerminado) VALUES 
					(8, 6, "Cantidad de goles en un partido", "[Cantidad de goles en un partido] (L) Independiente Vs Rosario Central (V) |Primera División Argentina| - 02/12 19:15Hs", FALSE);

-- Creando un evento de tipo "Cantidad de goles par o impar" (Par, Impar)
INSERT INTO Evento 	(id, id_partido, nombre, descripcion, isTerminado) VALUES 
					(9, 7, "Cantidad de goles par o impar", "[Cantidad de goles par o impar] (L) Gimnasia LP Vs River Plate (V) |Primera División Argentina| - 03/12 21:30Hs", FALSE);


-- Creando cuotas que son asignadas a los eventos de tipo "Resultado"
INSERT INTO Cuota 	(id, evento_id, nombre, valor, cantidadVotos) VALUES
					(1, 1, "Gana China", 4.90, 0),
					(2, 1, "Empate", 2.32, 0),
 					(3, 1, "Gana Argentina", 1.44, 0),

 					(4, 2, "Gana Argentina", 1.44, 0),
 					(5, 2, "Empate", 2.32, 0),
 					(6, 2, "Gana China", 4.90, 0),

					(7, 3, "Gana Napoli", 1.89, 0),
					(8, 3, "Empate", 2.08, 0),
 					(9, 3, "Gana Juventus", 2.5, 0),

 					(10, 4, "Gana Barcelona", 1.55, 0),
 					(11, 4, "Empate", 2.93, 0),
 					(12, 4, "Gana Celta de Vigo", 3.78, 0),

					(13, 5, "Gana Racing de Estraburgo", 5.03, 0),
 					(14, 5, "Empate", 2.76, 0),
 					(15, 5, "Gana PSG", 1.48, 0),

 					(16, 6, "Gana Independiente", 1.79, 0),
 					(17, 6, "Empate", 2.36, 0),
 					(18, 6, "Gana Rosario Central", 2.43, 0),

 					(19, 7, "Gana Gimnasia LP", 2.06, 0),
 					(20, 7, "Empate", 1.64, 0),
 					(21, 7, "Gana River Plate", 1.87, 0);

-- Creando cuotas que son asignadas a los eventos de tipo "Cantidad de goles en un partido"
INSERT INTO Cuota 	(id, evento_id, nombre, valor, cantidadVotos) VALUES
					(38, 8, "0", 1.65, 0),
 					(39, 8, "1", 1.90, 0),
					(40, 8, "2", 2.03, 0),
					(41, 8, "3", 2.24, 0),
					(42, 8, "4", 2.61, 0),
 					(43, 8, "+4", 2.72, 0);

-- Creando cuotas que son asignadas a los eventos de tipo "Cantidad de goles par o impar"
INSERT INTO Cuota 	(id, evento_id, nombre, valor, cantidadVotos) VALUES 
					(44, 9, "Par", 2.00, 0),
					(45, 9, "Impar", 2.00, 0);


-- Hardcodeando apuestas
INSERT INTO Apuesta	(id, evento_id, apostador_id, cantidadApostada, cuotaValor, cuotaNombre, isGanadora) VALUES
					(1, 1, 3, 20.50, 1.44, "Gana Argentina", TRUE),
					(2, 1, 4, 1500.00, 4.90, "Gana China", FALSE),
 					(3, 1, 5, 5.00, 1.36, "Gana Argentina", TRUE),
					(4, 2, 6, 900.00, 1.44, "Gana Argentina", FALSE),
 					(5, 2, 7, 2.33, 1.40, "Gana Argentina", FALSE);

-- Evento que setea los partidos finalizados (hay que dropear, no lo maneja Hibernate)
-- (Cada un minuto, comprueba si hay algun partido en que la fecha actual sea 2 horas 
-- mayor o igual a la fecha seteada para el partido y lo da como terminado)
DROP EVENT IF EXISTS TERMINAR_PARTIDO;
SET GLOBAL event_scheduler = ON;
CREATE EVENT TERMINAR_PARTIDO ON SCHEDULE EVERY 1 MINUTE DO
	UPDATE Partido P
	SET P.isTerminado=TRUE
	WHERE P.isTerminado=FALSE
		AND TIMESTAMPDIFF(MINUTE, P.fecha, NOW()) >= 120;

-- Trigger que setea las apuestas que tienen premio (hay que dropear, no lo maneja Hibernate)
-- (Actualiza el campo que define si una apuesta tiene premio o no. Esto va ocurrir siempre 
-- y cuando el nombre de la cuota apostada coincida con el nombre de la cuota ganadora y el 
-- evento modificado sea finalizado. Un trigger -para los que no cursaron BDD2- en pocas 
-- palabras seria algo que se dispara cuando ocurre una accion. En este caso, el trigger se 
-- dispara despues de un update -AFTER UPDATE- en la tabla Evento. El ultimo AND comprueba 
-- que en el registro actualizado se diera por terminado el evento. En un trigger, en este 
-- caso en MySQL, hay dos tabla llamadas 'NEW' y 'OLD'. Cuando el trigger se dispara por un 
-- INSERT, la tabla 'NEW' va tener las filas insertadas. Cuando se dispara por un DELETE, 
-- la tabla 'OLD' va tener las filas eliminadas. Cuando se realiza un UPDATE, como en este 
-- caso, las dos tablas van a tener registros. 'NEW' tiene las filas actualiza y 'OLD' las 
-- filas viejas).
DROP TRIGGER IF EXISTS PREMIAR_APUESTAS;
CREATE TRIGGER PREMIAR_APUESTAS AFTER UPDATE ON Evento
FOR EACH ROW 
	UPDATE Apuesta A
	JOIN Evento E ON A.evento_id=E.id
	SET A.isGanadora=TRUE
	WHERE A.isGanadora=FALSE
		AND A.cuotaNombre=E.cuotaGanadora
		AND NEW.isTerminado IS TRUE;	
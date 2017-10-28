package ar.edu.unlam.tallerweb1.modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

public class ApuestaTest extends SpringTest{	
	@Test
	@Transactional
	@Rollback(true)
	@SuppressWarnings("unchecked")
	public void testApuesta() {		
		//Creando un usuario
		Usuario pepe = new Usuario();
		pepe.setNombreYApellido("Pepe Pompin");
		//pepe.setApuestas(new HashSet<>());
		
		//Creando dos equipos de futbol
		Equipo Boca = new Equipo();
		Equipo River = new Equipo();
		Boca.setNombre("Boca Juniors");
		River.setNombre("River Plate");
		
		//Creando un partido
		Partido superClasico = new Partido();
		superClasico.setLocal(Boca);
		superClasico.setVisitante(River);
		
		//Creando un evento por el que se puede apostar
		Evento evento = new Evento();
		evento.setNombre("Resultado");
		evento.setPartido(superClasico);
		
		//Creando las cuotas que se pueden elegir en un evento
		Cuota victoriaLocal = new Cuota();
		Cuota victoriaVisitante = new Cuota();
		Cuota empate = new Cuota();
		victoriaLocal.setNombre("Gana Boca");
		victoriaLocal.setValor(2.35d);
		victoriaVisitante.setNombre("Gana River");
		victoriaVisitante.setValor(2.76d);
		empate.setNombre("Empate");
		empate.setValor(1.81d);
		
		//Creando un set de cuotas con las opciones recien creadas
		Set<Cuota> cuotas = new HashSet<Cuota>();
		cuotas.add(victoriaLocal);
		cuotas.add(empate);
		cuotas.add(victoriaVisitante);
		
		//Asignando las cuotas creadas al evento creado anteriormente
		evento.setCuotas(cuotas);
		
		//Creando una apuesta que realiza pepe para el evento denominado superclasico
		Apuesta apuesta = new Apuesta();
		apuesta.setApostador(pepe);
		apuesta.setCantidadApostada(10.00d);
		apuesta.setEvento(evento);
		
		/*Suponiendo que se le presenta las opciones al usuario y el elige "Gana Boca"
		 * - Selecciona el evento en el que quiere apostar (el del superclasico)
		 * - Se le ofrecen las cuotas correspondientes*/
		for (Cuota cuota : cuotas) {
			if(cuota.getNombre().equals("Gana Boca"))
				apuesta.setCuotaApostada(cuota.getValor());
		}		
		
		//Guardando en la BDD el usuario
		getSession().save(pepe);
		
		//Guardando en la BDD los equipos
		getSession().save(Boca);
		getSession().save(River);
		
		//Guardando en la BDD el partido
		getSession().save(superClasico);
		
		//Guardando en la BDD el evento
		getSession().save(evento);
		
		//Guardando en la BDD las cuotas
		getSession().save(victoriaLocal);
		getSession().save(victoriaVisitante);
		getSession().save(empate);
		
		//Guardando en la BDD la apuesta
		getSession().save(apuesta);	
		
				/*=======================================
				 *COMIENZO DE ALGUNOS ASSERTS DE PRUEBA!!
				 *======================================*/
		
		//Obteniendo las id de los partidos donde juega "Boca Juniors" de local
		List<Partido> idDePartidosDondeJuegaBocaDeLocal;
		idDePartidosDondeJuegaBocaDeLocal = getSession().createCriteria(Partido.class)
				.createAlias("local", "l")
				.add(Restrictions.eq("l.nombre", "Boca Juniors"))
				.list();		
		assertThat(idDePartidosDondeJuegaBocaDeLocal.get(0).getId()).isEqualTo(1L);		
		
		//Obteniendo los equipos que juegan un partido segun una id dada
		Partido comprobandoPartido = new Partido();
		Long idDadaComprobarPartido = 1L;
		comprobandoPartido = (Partido)getSession().createCriteria(Partido.class)
				.add(Restrictions.eq("id", idDadaComprobarPartido))
				.uniqueResult();
		assertThat(comprobandoPartido.getLocal().getNombre()).isEqualTo("Boca Juniors");
		assertThat(comprobandoPartido.getVisitante().getNombre()).isEqualTo("River Plate");
	}
}
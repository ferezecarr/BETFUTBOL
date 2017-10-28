package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;
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
		victoriaLocal.setEvento(evento);
		victoriaVisitante.setEvento(evento);
		empate.setEvento(evento);
		
		//Creando una apuesta que realiza pepe para el evento denominado superclasico
		Apuesta apuesta = new Apuesta();
		apuesta.setApostador(pepe);
		apuesta.setCantidadApostada(10.00d);
		apuesta.setEvento(evento);
		
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
		
		//Obteniendo los partidos donde juega "Boca Juniors" de local
		List<Partido> idDePartidosDondeJuegaBocaDeLocal;
		idDePartidosDondeJuegaBocaDeLocal = getSession().createCriteria(Partido.class)
				.createAlias("local", "l")
				.add(Restrictions.eq("l.nombre", "Boca Juniors"))
				.list();		
		for (Partido p : idDePartidosDondeJuegaBocaDeLocal) {
			assertThat(p.getLocal().getNombre()).isEqualTo("Boca Juniors");
		}
		
		//Obteniendo los equipos que juegan un partido segun una id dada
		Partido comprobandoPartido = new Partido();
		Long idDadaComprobarPartido = 1L;
		comprobandoPartido = (Partido)getSession().createCriteria(Partido.class)
				.add(Restrictions.eq("id", idDadaComprobarPartido))
				.uniqueResult();
		assertThat(comprobandoPartido.getLocal().getNombre()).isEqualTo("Boca Juniors");
		assertThat(comprobandoPartido.getVisitante().getNombre()).isEqualTo("River Plate");
		assertThat(comprobandoPartido.getGolesLocal()).isEqualTo(0);
		assertThat(comprobandoPartido.getGolesVisitante()).isEqualTo(0);
		
		//Obteniendo todos los eventos de tipo "Resultado"
		List<Evento> eventosDeTipoResultado;
		eventosDeTipoResultado = getSession().createCriteria(Evento.class)
				.add(Restrictions.eq("nombre", "Resultado"))
				.list();		
		for (Evento e : eventosDeTipoResultado) {
			assertThat(e.getNombre()).isEqualTo("Resultado");
		}
		
		/*Obteniendo todos los eventos de tipo "Resultado" donde hay un partido en el 
		 * que "River Plate" es visitante.
		 * NOTA: Notese que hay dos 'createCriteria'. Usar tres 'createAlias' no funciona
		 * (Me gustaria entender porque)*/
		List<Evento> eventosDeTipoResultadoDondeRiverEsVisitante;
		eventosDeTipoResultadoDondeRiverEsVisitante = getSession().createCriteria(Evento.class)
				.add(Restrictions.eq("nombre", "Resultado"))
				.createCriteria("partido")
				.createAlias("visitante", "v")
				.add(Restrictions.eq("v.nombre", "River Plate"))
				.list();	
		for (Evento e : eventosDeTipoResultadoDondeRiverEsVisitante) {
			assertThat(e.getNombre()).isEqualTo("Resultado");
			assertThat(e.getPartido().getVisitante().getNombre()).isEqualTo("River Plate");
		}
		
		/*Obteniendo las cuotas correspondientes a un evento de una determinada id, 
		 * que sean de tipo "Resultado" y que el nombre de la cuota sea "Empate"*/		
		List<Cuota> eventosResultadoQueTienenCuotaEmpate;
		Long determinadaId = 1L;
		eventosResultadoQueTienenCuotaEmpate = getSession().createCriteria(Cuota.class)
				.add(Restrictions.eq("nombre", "Empate"))
				.createAlias("evento", "e")
				.add(Restrictions.eq("e.id", determinadaId))
				.add(Restrictions.eq("e.nombre", "Resultado"))
				.list();
		assertThat(eventosResultadoQueTienenCuotaEmpate).hasSize(1);
		assertThat(eventosResultadoQueTienenCuotaEmpate.get(0).getEvento().getId()).isEqualTo(determinadaId);
		assertThat(eventosResultadoQueTienenCuotaEmpate.get(0).getNombre()).isEqualTo("Empate");
		assertThat(eventosResultadoQueTienenCuotaEmpate.get(0).getEvento().getNombre()).isEqualTo("Resultado");

		
		
		//ToDo: Traer las cuotas de un evento, en el que hay un partido donde juega River		
		
		
				
	}
}
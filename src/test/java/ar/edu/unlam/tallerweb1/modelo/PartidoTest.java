package ar.edu.unlam.tallerweb1.modelo;

import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

@SuppressWarnings("unchecked")
public class PartidoTest extends SpringTest{	
	private Jugador jugador1, jugador2, jugador3, jugador4;
	private Partido partido;
	private Equipo local;
	private Equipo visitante;
	
	@Before
	public void inicializacionAntesDeCadaTest(){
		jugador1 = new Jugador();
		jugador2 = new Jugador();
		jugador3 = new Jugador();
		jugador4 = new Jugador();
		partido = new Partido();
		local = new Equipo();
		visitante = new Equipo();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeLosJugadoresDeUnPartido(){		
		//Seteo los nombres de los equipos
		local.setNombre("River Plate");
		visitante.setNombre("Boca Juniors");		
		
		//Seteo nombre y posicion de 4 jugadores
		jugador1.setNombre("German Lux");
		jugador1.setPosicion("Arquero");
		jugador2.setNombre("Rodrigo Mora");
		jugador2.setPosicion("Delantero");
		jugador3.setNombre("Guillermo Sara");
		jugador3.setPosicion("Arquero");
		jugador4.setNombre("Dario Benedetto");
		jugador4.setPosicion("Delantero");
		
		//Indico equipo de cada jugador
		jugador1.setEquipo(local);
		jugador2.setEquipo(local);
		jugador3.setEquipo(visitante);
		jugador4.setEquipo(visitante);		
		
		//Seteo el partido
		partido.setLocal(local);
		partido.setVisitante(visitante);
		
		//Guardo las cosas
		getSession().save(local);
		getSession().save(visitante);
		getSession().save(jugador1);
		getSession().save(jugador2);
		getSession().save(jugador3);
		getSession().save(jugador4);
		getSession().save(partido);
		
		//Recupero el partido
		partido = new Partido();
		partido = (Partido)getSession().createCriteria(Partido.class)
				.uniqueResult();
		
		assertThat(partido.getIsResultadoFinal()).isFalse();
		assertThat(partido.getLocal().getNombre()).isEqualTo("River Plate");
		assertThat(partido.getVisitante().getNombre()).isEqualTo("Boca Juniors");
		
		//Traigo jugadores que jugaron el partido
		List<Jugador> jugadoresLocal = getSession().createCriteria(Jugador.class)
				.add(Restrictions.eq("equipo", partido.getLocal()))
				.list();
		List<Jugador> jugadoresVisitante = getSession().createCriteria(Jugador.class)
				.add(Restrictions.eq("equipo", partido.getVisitante()))
				.list();
		
		assertThat(jugadoresLocal).hasSize(2);
		assertThat(jugadoresVisitante).hasSize(2);
		assertThat(jugadoresLocal.get(0).getNombre()).isEqualTo("German Lux");
		assertThat(jugadoresLocal.get(1).getNombre()).isEqualTo("Rodrigo Mora");
		assertThat(jugadoresVisitante.get(0).getNombre()).isEqualTo("Guillermo Sara");
		assertThat(jugadoresVisitante.get(1).getNombre()).isEqualTo("Dario Benedetto");		
	}
}
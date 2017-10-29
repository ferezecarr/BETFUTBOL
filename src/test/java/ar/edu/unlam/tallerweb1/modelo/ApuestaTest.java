/*NOTA: En relaciones bidireccionales, aparentemente hay que setear los dos lados para 
 * que quede bien la relacion. Despues el metodo .save, si creo que se puede usar de 
 * un lado solo (el 'cascade' hace la magia)*/
package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

@SuppressWarnings("unchecked")
public class ApuestaTest extends SpringTest{	
	private Usuario usuario1, usuario2;
	private Equipo equipo1, equipo2, equipo3;
	private Partido partido1, partido2;
	private Evento evento1, evento2;
	private Cuota cuota1, cuota2, cuota3, cuota4;
	private @SuppressWarnings("rawtypes")List lCuotas1, lCuotas2;
	private Apuesta apuesta1, apuesta2;
	private @SuppressWarnings("rawtypes")List lApuestas1, lApuestas2;
	
	@Before
	public void inicializacionAntesDeCadaTest(){
		usuario1	=		new Usuario();
		usuario2	=		new Usuario();
		equipo1		=		new Equipo();
		equipo2		=		new Equipo();
		equipo3		=		new Equipo();
		partido1	=		new Partido();
		partido2	=		new Partido();
		evento1		=		new Evento();
		evento2		=		new Evento();
		cuota1		=		new Cuota();
		cuota2		=		new Cuota();
		cuota3		=		new Cuota();
		cuota4		=		new Cuota();
		apuesta1	=		new Apuesta();
		apuesta2	=		new Apuesta();
		lCuotas1 	=		new LinkedList<Cuota>();
		lCuotas2	=		new LinkedList<Cuota>();
		lApuestas1	=		new LinkedList<Apuesta>();
		lApuestas2	=		new LinkedList<Apuesta>();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeLosPartidosDondeArgentinaJuegaDeLocal(){
		equipo1.setNombre("Argentina");
		equipo2.setNombre("Brasil");
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		partido2.setLocal(equipo2);
		partido2.setVisitante(equipo1);
		
		/*Si se obvian los saves del equipos, no se puede armar la lista cuando se trae 
		 * desde criteria los partidos*/
		getSession().save(equipo1);
		getSession().save(equipo2);		
		getSession().save(partido1);
		getSession().save(partido2);
		
		List<Equipo> listaEquipos = getSession().createCriteria(Equipo.class)
				.list();
		
		List<Partido> listaPartidos = getSession().createCriteria(Partido.class)
				.createAlias("local", "l")
				.add(Restrictions.eq("l.nombre", "Argentina"))
				.list();
		
		assertThat(listaPartidos).hasSize(1);
		assertThat(listaPartidos.get(0).getLocal().getNombre()).isEqualTo("Argentina");
		assertThat(listaPartidos.get(0).getVisitante().getNombre()).isEqualTo("Brasil");
		assertThat(listaEquipos).hasSize(2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeLosEquiposQueSeEnfrentanEnUnEventoDeTipoResultado(){
		equipo1.setNombre("Mexico");
		equipo2.setNombre("Costa Rica");
		equipo3.setNombre("Panama");
		getSession().save(equipo1);
		getSession().save(equipo2);
		getSession().save(equipo3);
		
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		partido2.setLocal(equipo3);
		partido2.setVisitante(equipo1);
		getSession().save(partido1);
		getSession().save(partido2);
		
		evento1.setPartido(partido1);
		evento1.setNombre("Resultado Especifico");
		evento2.setPartido(partido2);
		evento2.setNombre("Resultado");
		getSession().save(evento1);
		getSession().save(evento2);
		
		//Asegurandome de que se crean la cantidad de cosas que espero
		assertThat(getSession().createCriteria(Equipo.class).list()).hasSize(3);
		assertThat(getSession().createCriteria(Partido.class).list()).hasSize(2);
		assertThat(getSession().createCriteria(Evento.class).list()).hasSize(2);
		
		List<Evento> lista = getSession().createCriteria(Evento.class)
				.add(Restrictions.eq("nombre", "Resultado"))
				.list();
		
		assertThat(lista).hasSize(1);
		assertThat(lista.get(0).getPartido().getLocal().getNombre()).isEqualTo("Panama");
		assertThat(lista.get(0).getPartido().getVisitante().getNombre()).isEqualTo("Mexico");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeLasCuotasDeUnEventoDeTipoResultado(){
		//Seteo equipos y los guardo
		equipo1.setNombre("Barcelona");
		equipo2.setNombre("Real Madrid");
		equipo3.setNombre("Milan");
		getSession().save(equipo1);
		getSession().save(equipo2);
		getSession().save(equipo3);
		
		//Seteo partidos y los guardo
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		partido2.setLocal(equipo2);
		partido2.setVisitante(equipo3);
		getSession().save(partido1);
		getSession().save(partido2);
		
		//Seteo cuotas
		cuota1.setNombre("Gana el local");
		cuota1.setValor(2.05d);
		cuota2.setNombre("Empate");
		cuota2.setValor(2.63d);
		cuota3.setNombre("Gana el visitante");
		cuota3.setValor(2.99d);
		cuota4.setNombre("Gana Milan");
		cuota4.setValor(2.33d);
		
		//Asigno un evento a cada cuota
		cuota1.setEvento(evento2);
		cuota2.setEvento(evento2);
		cuota3.setEvento(evento2);
		cuota4.setEvento(evento1);
		
		//Seteo eventos
		evento1.setNombre("Jugador hace un gol");
		evento2.setNombre("Resultado");
		evento1.setPartido(partido1);
		evento2.setPartido(partido2);
		
		//Asigno una lista de cuotas a cada evento
		lCuotas1.add(cuota4);
		lCuotas2.add(cuota1);
		lCuotas2.add(cuota2);
		lCuotas2.add(cuota3);				
		evento1.setCuotas(lCuotas1);
		evento2.setCuotas(lCuotas2);
		
		//Guardo cuotas
		getSession().save(cuota1);
		getSession().save(cuota2);
		getSession().save(cuota3);
		getSession().save(cuota4);
		
		//Guardo eventos
		getSession().save(evento1);
		getSession().save(evento2);
		
		//Asegurando que las cosas se guardaron como esperaba
		assertThat(getSession().createCriteria(Equipo.class).list()).hasSize(3);
		assertThat(getSession().createCriteria(Partido.class).list()).hasSize(2);
		assertThat(getSession().createCriteria(Evento.class).list()).hasSize(2);
		assertThat(getSession().createCriteria(Cuota.class).list()).hasSize(4);
		
		List<Cuota> lista;
		lista = getSession().createCriteria(Cuota.class)
				.createAlias("evento", "e")
				.add(Restrictions.eq("e.nombre", "Resultado"))
				.list();		
	
		assertThat(lista).hasSize(3);
		assertThat(lista.get(0).getNombre()).isEqualTo("Gana el local");
		assertThat(lista.get(1).getNombre()).isEqualTo("Empate");
		assertThat(lista.get(2).getNombre()).isEqualTo("Gana el visitante");
		for (Cuota cuota : lista) {
			assertThat(cuota.getEvento().getNombre()).isEqualTo("Resultado");
		}	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeUsuariosQueApostaronPorUnEmpateEnUnEventoDondeEnElPartidoBarcelonaEsLocal(){
		//Seteo usuarios y los guardo		
		usuario1.setNombreYApellido("Pepe Pompin");
		usuario2.setNombreYApellido("Juan Perez");
		getSession().save(usuario1);
		getSession().save(usuario2);		
		
		//Seteo equipos y los guardo
		equipo1.setNombre("Barcelona");
		equipo2.setNombre("Real Madrid");
		getSession().save(equipo1);
		getSession().save(equipo2);
		
		//Seteo partidos y los guardo
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		getSession().save(partido1);
		
		//Seteo cuotas
		cuota1.setNombre("Gana el local");
		cuota1.setValor(2.05d);
		cuota2.setNombre("Empate");
		cuota2.setValor(2.63d);
		cuota3.setNombre("Gana el visitante");
		cuota3.setValor(2.99d);
		
		//Asigno un evento a cada cuota
		cuota1.setEvento(evento1);
		cuota2.setEvento(evento1);
		cuota3.setEvento(evento1);
		
		//Seteo eventos
		evento1.setNombre("Resultado");
		evento1.setPartido(partido1);
		
		//Asigno una lista de cuotas a cada evento
		lCuotas1.add(cuota1);
		lCuotas1.add(cuota2);
		lCuotas1.add(cuota3);				
		evento1.setCuotas(lCuotas1);
		
		//Guardo cuotas
		getSession().save(cuota1);
		getSession().save(cuota2);
		getSession().save(cuota3);
		
		//Guardo eventos
		getSession().save(evento1);	
		
		//Seteo apuestas (Tambien del lado del usuario, por la bidireccionalidad)
		apuesta1.setApostador(usuario1);
		apuesta1.setEvento(evento1);
		apuesta1.setCuotaNombre("Empate");
		apuesta1.setCantidadApostada(100d);
		apuesta1.setCuotaValor(2.63d);	
		
		apuesta2.setApostador(usuario2);		
		apuesta2.setEvento(evento1);		
		apuesta2.setCuotaNombre("Gana el visitante");		
		apuesta2.setCantidadApostada(25d);		
		apuesta2.setCuotaValor(2.99d);
		
		lApuestas1.add(apuesta1);
		lApuestas2.add(apuesta2);
		usuario1.setApuestas(lApuestas1);
		usuario2.setApuestas(lApuestas2);
		
		//Guardo apuestas		
		getSession().save(apuesta1);
		getSession().save(apuesta2);
		
		List<Usuario> lista;
		lista = getSession().createCriteria(Usuario.class)
				.createAlias("apuestas", "a")
				.createAlias("a.evento", "e")
				.add(Restrictions.eq("a.cuotaNombre", "Empate"))
				.createAlias("e.partido", "p")
				.createAlias("p.local", "l")
				.add(Restrictions.eq("l.nombre", "Barcelona"))				
				.list();
		
		assertThat(getSession().createCriteria(Usuario.class).list()).hasSize(2);
		assertThat(lista).hasSize(1);
		assertThat(lista.get(0).getApuestas()).hasSize(1);
		assertThat(lista.get(0).getApuestas().get(0).getEvento().getCuotas()).hasSize(3);
		assertThat(lista.get(0).getApuestas().get(0).getEvento().getCuotas().get(0).getNombre()).isEqualTo("Gana el local");
		assertThat(lista.get(0).getApuestas().get(0).getEvento().getCuotas().get(1).getNombre()).isEqualTo("Empate");
		assertThat(lista.get(0).getApuestas().get(0).getEvento().getCuotas().get(2).getNombre()).isEqualTo("Gana el visitante");
		assertThat(lista.get(0).getNombreYApellido()).isEqualTo("Pepe Pompin");
		assertThat(lista.get(0).getApuestas().get(0).getCuotaNombre()).isEqualTo("Empate");
	}
}
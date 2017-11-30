/*NOTA: En relaciones bidireccionales, aparentemente hay que setear los dos lados para 
 * que quede bien la relacion. Despues el metodo .save, si creo que se puede usar de 
 * un lado solo (el 'cascade' hace la magia)*/
package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

@SuppressWarnings("unchecked")
public class ApuestaTest extends SpringTest{	
	private Usuario usuario1, usuario2, usuario3;
	private Equipo equipo1, equipo2, equipo3;
	private Partido partido1, partido2;
	private Evento evento1, evento2;
	private Cuota cuota1, cuota2, cuota3, cuota4;
	private @SuppressWarnings("rawtypes")List lCuotas1;
	private Apuesta apuesta1, apuesta2, apuesta3;
	private @SuppressWarnings("rawtypes")List lApuestas1, lApuestas2, lApuestas3;
	
	@Before
	public void inicializacionAntesDeCadaTest(){
		usuario1	=		new Usuario();
		usuario2	=		new Usuario();
		usuario3	=		new Usuario();
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
		apuesta3	=		new Apuesta();
		lCuotas1 	=		new LinkedList<Cuota>();
		lApuestas1	=		new LinkedList<Apuesta>();
		lApuestas2	=		new LinkedList<Apuesta>();
		lApuestas3	=		new LinkedList<Apuesta>();
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
		
		//Seteo eventos
		evento1.setNombre("Jugador hace un gol");
		evento2.setNombre("Resultado");
		evento1.setPartido(partido1);
		evento2.setPartido(partido2);
		

		//Asigno eventos a cuotas
		cuota1.setEvento(evento2);
		cuota2.setEvento(evento2);
		cuota3.setEvento(evento2);
		cuota4.setEvento(evento1);
		
		//Guardo eventos (La cascada guarda las cuotas)
		getSession().save(evento1);
		getSession().save(evento2);
		getSession().save(cuota1);
		getSession().save(cuota2);
		getSession().save(cuota3);
		getSession().save(cuota4);
		
		
		//Asegurando que las cosas se guardaron como esperaba
		assertThat(getSession().createCriteria(Equipo.class).list()).hasSize(3);
		assertThat(getSession().createCriteria(Partido.class).list()).hasSize(2);
		assertThat(getSession().createCriteria(Evento.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list()).hasSize(2);
		assertThat(getSession().createCriteria(Cuota.class).list()).hasSize(4);
		
		List<Cuota> lista;
		lista = getSession().createCriteria(Cuota.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
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
	public void testQueTraeApuestasPorUnEmpateEnUnEventoDondeEnElPartidoBarcelonaEsLocal(){
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
		
		//Seteo eventos
		evento1.setNombre("Resultado");
		evento1.setPartido(partido1);
		
		//Asigno cuotas a un evento
		evento1.addCuota(cuota1);
		evento1.addCuota(cuota2);
		evento1.addCuota(cuota3);
		
		//Guardo eventos (La cascada guarda las cuotas)
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
		
		//Guardo apuestas		
		getSession().save(apuesta1);
		getSession().save(apuesta2);
		
		
		List<Apuesta> lista;
		lista = getSession().createCriteria(Apuesta.class)
				.add(Restrictions.eq("cuotaNombre", "Empate"))
				.createAlias("evento", "e")
				.createAlias("e.partido", "p")
				.createAlias("p.local", "l")
				.add(Restrictions.eq("l.nombre", "Barcelona"))
				.list();
		
		assertThat(lista).hasSize(1);
		assertThat(lista.get(0).getEvento().getCuotas()).hasSize(3);
		assertThat(lista.get(0).getEvento().getCuotas().get(0).getNombre()).isEqualTo("Gana el local");
		assertThat(lista.get(0).getEvento().getCuotas().get(1).getNombre()).isEqualTo("Empate");
		assertThat(lista.get(0).getEvento().getCuotas().get(2).getNombre()).isEqualTo("Gana el visitante");	
		assertThat(lista.get(0).getApostador().getNombreYApellido()).isEqualTo("Pepe Pompin");
		assertThat(lista.get(0).getCuotaNombre()).isEqualTo("Empate");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeEventosDeTipoResultadoYAccedeATodasLasRelaciones(){
		//Seteo equipos y los guardo
		equipo1.setNombre("Nueva Zelanda");
		equipo2.setNombre("Peru");
		getSession().save(equipo1);
		getSession().save(equipo2);
		
		//Seteo partidos y los guardo
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		getSession().save(partido1);
		getSession().save(partido2);
		
		//Seteo cuotas
		cuota1.setNombre("Gana el local");
		cuota1.setValor(2.99d);
		cuota2.setNombre("Empate");
		cuota2.setValor(2.63d);
		cuota3.setNombre("Gana el visitante");
		cuota3.setValor(2.05d);
		
		//Asigno un evento a cada cuota (seria como setear el evento_id en la tabla Cuota)
		cuota1.setEvento(evento1);
		cuota2.setEvento(evento1);
		cuota3.setEvento(evento1);
		
		//Seteo eventos
		evento1.setNombre("Resultado");
		evento1.setPartido(partido1);
		
		/*Asigno una lista de cuotas a cada evento...
		 * (Por la bidireccionalidad, si no hago esto, no puedo acceder a las cuotas 
		 * desde un evento -la lista de cuotas vendria vacia-. Si se podria al reves, 
		 * haciendo una lista de Cuotas y filtrando por eventos, pero la idea era 
		 * traer una lista de Eventos, no de Cuotas -ademas de que suena raro-)*/
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
		
		List<Evento> lista = getSession().createCriteria(Evento.class)
				.add(Restrictions.eq("nombre", "Resultado"))
				.list();
		
		//Demostrando que se puede accerder
		assertThat(lista).hasSize(1);
		assertThat(lista.get(0).getCuotas()).hasSize(3);
		assertThat(lista.get(0).getCuotas().get(0).getNombre()).isEqualTo("Gana el local");
		assertThat(lista.get(0).getCuotas().get(1).getNombre()).isEqualTo("Empate");
		assertThat(lista.get(0).getCuotas().get(2).getNombre()).isEqualTo("Gana el visitante");
		assertThat(lista.get(0).getPartido().getLocal().getNombre()).isEqualTo("Nueva Zelanda");
		assertThat(lista.get(0).getPartido().getVisitante().getNombre()).isEqualTo("Peru");
		assertThat(lista.get(0).getCuotas().get(0).getValor()).isEqualTo(2.99d);
		assertThat(lista.get(0).getCuotas().get(1).getValor()).isEqualTo(2.63d);
		assertThat(lista.get(0).getCuotas().get(2).getValor()).isEqualTo(2.05d);
		
		//Eso es solo para imprimir en consola y ver que los datos se pueden formatear
		System.out.println("==============================================");
		for (Evento e : lista) {
			System.out.print(e.getPartido().getLocal().getNombre() + 
					" Vs " + 
					e.getPartido().getVisitante().getNombre() +
					" - ");
			for (Cuota c : e.getCuotas()) {
				System.out.print(c.getNombre() + ": " + c.getValor() + ". ");
			}
			System.out.println();	//Para dejar una linea
		}
		System.out.println("==============================================");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testQueTraeRankingDeGanadoresPorResultado(){
		Usuario usuario4 = new Usuario();
		Apuesta apuesta4 = new Apuesta();
		Apuesta apuesta5 = new Apuesta();
		List<Apuesta> lApuestas4 = new LinkedList<Apuesta>();
		List<Apuesta> lApuestas5 = new LinkedList<Apuesta>();
		
		//Seteo usuarios y los guardo		
		usuario1.setNombreYApellido("Yo acierto");
		usuario2.setNombreYApellido("Yo pierdo");
		usuario3.setNombreYApellido("Aposte otra cosa");
		usuario4.setNombreYApellido("Yo gano mas");
		getSession().save(usuario1);
		getSession().save(usuario2);
		getSession().save(usuario3);
		getSession().save(usuario4);
		
		//Seteo equipos y los guardo
		equipo1.setNombre("Honduras");
		equipo2.setNombre("Australia");
		getSession().save(equipo1);
		getSession().save(equipo2);
		
		//Seteo el partido y lo guardo
		partido1.setLocal(equipo1);
		partido1.setVisitante(equipo2);
		getSession().save(partido1);
		getSession().save(partido2);
		
		//Seteo cuotas
		cuota1.setNombre("Gana el local");
		cuota1.setValor(2.33d);
		cuota2.setNombre("Empate");
		cuota2.setValor(2.63d);
		cuota3.setNombre("Gana el visitante");
		cuota3.setValor(2.55d);
		
		//Asigno un evento a cada cuota (seria como setear el evento_id en la tabla Cuota)
		cuota1.setEvento(evento1);
		cuota2.setEvento(evento1);
		cuota3.setEvento(evento1);
		
		//Seteo eventos
		evento1.setNombre("Resultado");
		evento1.setPartido(partido1);
		evento2.setNombre("Otra cosa");
		evento2.setPartido(partido1);
		
		//Guardo cuotas y eventos
		lCuotas1.add(cuota1);
		lCuotas1.add(cuota2);
		lCuotas1.add(cuota3);				
		evento1.setCuotas(lCuotas1);
		getSession().save(cuota1);
		getSession().save(cuota2);
		getSession().save(cuota3);
		getSession().save(evento1);	
		getSession().save(evento2);
		
		//Genero apuestas y las guardo
		apuesta1.setApostador(usuario1);
		apuesta1.setCantidadApostada(100.00d);
		apuesta1.setCuotaNombre("Gana el local");
		apuesta1.setCuotaValor(2.33d);
		apuesta1.setEvento(evento1);
		
		apuesta2.setApostador(usuario2);
		apuesta2.setCantidadApostada(100.00d);
		apuesta2.setCuotaNombre("Empate");
		apuesta2.setCuotaValor(2.63d);
		apuesta2.setEvento(evento1);
		
		apuesta3.setApostador(usuario3);
		apuesta3.setCantidadApostada(100.00d);
		apuesta3.setCuotaNombre("Cualquier cosa");
		apuesta3.setCuotaValor(1.00d);
		apuesta3.setEvento(evento2);
		
		apuesta4.setApostador(usuario4);
		apuesta4.setCantidadApostada(200.00d);
		apuesta4.setCuotaNombre("Gana el local");
		apuesta4.setCuotaValor(1.00d);
		apuesta4.setEvento(evento1);
		
		apuesta5.setApostador(usuario4);
		apuesta5.setCantidadApostada(50.00d);
		apuesta5.setCuotaNombre("Gana el local");
		apuesta5.setCuotaValor(1.00d);
		apuesta5.setEvento(evento1);
		
		lApuestas1.add(apuesta1);
		lApuestas2.add(apuesta2);
		lApuestas3.add(apuesta3);
		lApuestas4.add(apuesta4);
		lApuestas5.add(apuesta5);
		//usuario1.setApuestas(lApuestas1);
		//usuario2.setApuestas(lApuestas2);
		//usuario3.setApuestas(lApuestas3);
		//usuario4.setApuestas(lApuestas4);
		//usuario4.setApuestas(lApuestas5);
		
		getSession().save(apuesta1);
		getSession().save(apuesta2);
		getSession().save(apuesta3);
		getSession().save(apuesta4);
		getSession().save(apuesta5);		
		
		//Falta filtar por si tiene premio o no
		String filtro = "Resultado";
		String sql = 	"SELECT "
					+ 		"U.nombreYApellido AS Usuario, "
					+ 		"SUM(A.cantidadApostada * A.cuotaValor) AS Ganancia "
					+ 	"FROM Usuario U "
					+ 		"JOIN Apuesta A ON U.id=A.apostador_id "
					+ 		"JOIN Evento E ON A.evento_id=E.id "
					+ 	"WHERE E.nombre='" + filtro + "' "
					+ 	"GROUP BY U.id "
					+ 	"ORDER BY Ganancia DESC "
					+	"LIMIT 5";
		
		List<RankingDTO> rankings = getSession().createSQLQuery(sql)
				.addScalar("usuario")
				.addScalar("ganancia")
				.setResultTransformer(Transformers.aliasToBean(RankingDTO.class))				
				.list();
		
		for (RankingDTO ranking : rankings) {
			System.out.println("==="+ranking.getUsuario()+"/"+ranking.getGanancia());
		}
		
		assertThat(rankings).hasSize(3);
	}
}
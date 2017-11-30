//Esta clase, siempre que no altere el valor de las cuotas, devuelve la lista que se le dio
package ar.edu.unlam.tallerweb1.modelo;

import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.tallerweb1.SpringTest;


public class CalculadorDeCuotasTest extends SpringTest{	
	private Evento evento;
	private Cuota cuota1;
	private Cuota cuota2;
	private Cuota cuota3;
	private List<Cuota> cuotasDevueltas;
	
	@Before
	public void inicializacion(){
		evento = new Evento();
		cuota1 = new Cuota();
		cuota2 = new Cuota();
		cuota3 = new Cuota();
		cuotasDevueltas = new LinkedList<Cuota>();
		
		cuota1.setId(1L);
		cuota2.setId(2L);
		cuota3.setId(3L);		
		
		cuota1.setNombre("Cuota 1");		
		cuota2.setNombre("Cuota 2");
		cuota3.setNombre("Cuota 3");
		
		cuota1.setValor(3.81d);
		cuota2.setValor(2.99d);
		cuota3.setValor(1.89d);		
		
		evento.addCuota(cuota1);
		evento.addCuota(cuota2);
		evento.addCuota(cuota3);		
	}
	
	@Test
	public void testQueDetectaUnNombreDeCuotaInvalido(){
		cuotasDevueltas = CalculadorDeCuotas.calcular(evento.getCuotas(), "No estoy");
		
		assertThat(cuotasDevueltas.get(0).getValor()).isEqualTo(3.81d);
		assertThat(cuotasDevueltas.get(1).getValor()).isEqualTo(2.99d);
		assertThat(cuotasDevueltas.get(2).getValor()).isEqualTo(1.89d);
	}
	
	@Test
	public void testQueDetectaQueLaCuotaVotadaNoEsLaMasVotada(){		
		cuota1.setCantidadVotos(10L);
		cuota2.setCantidadVotos(2L);
		cuota3.setCantidadVotos(1L);
		
		cuotasDevueltas = CalculadorDeCuotas.calcular(evento.getCuotas(), "Cuota 3");
		
		assertThat(cuotasDevueltas.get(0).getValor()).isEqualTo(3.81d);
		assertThat(cuotasDevueltas.get(1).getValor()).isEqualTo(2.99d);
		assertThat(cuotasDevueltas.get(2).getValor()).isEqualTo(1.89d);		
	}
	
	@Test
	public void testQueDetectaMasDeUnaOpcionQueEsLaMasVotada(){		
		cuota1.setCantidadVotos(10L);
		cuota2.setCantidadVotos(2L);
		cuota3.setCantidadVotos(10L);
		
		cuotasDevueltas = CalculadorDeCuotas.calcular(evento.getCuotas(), "Cuota 3");
		
		assertThat(cuotasDevueltas.get(0).getValor()).isEqualTo(3.81d);
		assertThat(cuotasDevueltas.get(1).getValor()).isEqualTo(2.99d);
		assertThat(cuotasDevueltas.get(2).getValor()).isEqualTo(1.89d);		
	}
	
	@Test
	public void testQueGeneraNuevasCuotasPeroMantieneLaMasVotadaSiEstaEnElValorMinimo(){
		cuota1.setCantidadVotos(10L);
		cuota2.setCantidadVotos(2L);
		cuota3.setCantidadVotos(9L);		
		
		cuota1.setValor(1.26d);	//Si puede bajar mas de 1.25d no se hace modificacion
		
		cuotasDevueltas = CalculadorDeCuotas.calcular(evento.getCuotas(), "Cuota 1");
		
		assertThat(cuotasDevueltas.get(0).getValor()).isEqualTo(1.26d);
		assertThat(cuotasDevueltas.get(1).getValor()).isNotEqualTo(2.99d);
		assertThat(cuotasDevueltas.get(2).getValor()).isNotEqualTo(1.89d);			
	}
	
	@Test
	public void testQueSiGeneraNuevasCuotas(){		
		cuota1.setCantidadVotos(10L);
		cuota2.setCantidadVotos(2L);
		cuota3.setCantidadVotos(9L);
		
		cuotasDevueltas = CalculadorDeCuotas.calcular(evento.getCuotas(), "Cuota 1");
		
		assertThat(cuotasDevueltas.get(0).getValor()).isNotEqualTo(3.81d);
		assertThat(cuotasDevueltas.get(1).getValor()).isNotEqualTo(2.99d);
		assertThat(cuotasDevueltas.get(2).getValor()).isNotEqualTo(1.89d);		
	}
}
/*Despue de cada apuesta, llamar a 'CalculadorDeCuotas.calcular()', ej...
 * 'listaCuotas = CalculadorDeCuotas.calcular(listaCuotas, apuesta.getCuotaNombre());'
 * Se dan dos argumentos: 
 * 1 - La lista de cuotas
 * 2 - El nombre de la cuota de la apuesta
 * 
 * NOTA: Se asume que la lista y el String que llegan aca no tienen alguno de los campos 
 * utiles en null. Despues veo de agregar validaciones, aunque supongo que se deberia 
 * controlar eso antes de llamar a este metodo. ESTA CLASE NO ES PERSISTIBLE*/
package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

public class CalculadorDeCuotas {
	public static List<Cuota> calcular(List<Cuota> cuotas, String cuotaNombreVotada){
		String nombreCuota;		//Guarda el nombre de la cuota ma votada
		
		/*Si no existe una cuota con el nombre que se da en el segundo parametro, se devuelve 
		la misma lista*/
		if(!existeLaCuota(cuotas,cuotaNombreVotada))
			return cuotas;			
		
		/*Se obtiene el nombre de la cuota mas votada y se comprueba que sea igual al de la 
		apuesta (Solo se recalcula cuando se vota a la opcion mas votada). Si esto no se 
		cumple, se devuelve la misma lista*/
		nombreCuota = obtenerCuotaNombreMasVotada(cuotas);
		if(!nombreCuota.equals(cuotaNombreVotada))
			return cuotas;
		
		//Solo se aplica el calculo si hay una unica opcion que es la mas votada
		if(existeEmpateEnLaVotacionMaxima(cuotas, cuotaNombreVotada))
			return cuotas;
		
		//Se llama a este metodo que devuelve la nuevas cuotas
		return generarNuevasCuotas(cuotas, nombreCuota);
	}
	
	private static Boolean existeLaCuota(List<Cuota> cuotas, String cuotaNombreVotada){
		Boolean existe = false;
		for (Cuota cuota : cuotas) {
			if(cuota.getNombre().equals(cuotaNombreVotada)){
				existe = true;
			}
		}	
		return existe;	
	}
	
	private static String obtenerCuotaNombreMasVotada(List<Cuota> cuotas){
		Long votos = 0L;
		String nombre = null;
		for (Cuota cuota : cuotas) {
			if(cuota.getCantidadVotos() > votos){
				votos = cuota.getCantidadVotos();
				nombre = cuota.getNombre();
			}				
		}		
		return nombre;
	}
	
	private static Boolean existeEmpateEnLaVotacionMaxima(List<Cuota> cuotas, String cuotaNombreVotada){
		Long votosMax = 0L;
		Long idVotosMax = 0L;
		for (Cuota cuota : cuotas) {
			if(cuota.getNombre().equals(cuotaNombreVotada)){
				votosMax = cuota.getCantidadVotos();
				idVotosMax = cuota.getId();
			}			
		}
		for (Cuota cuota : cuotas) {
			if(cuota.getCantidadVotos().equals(votosMax) && !(cuota.getId().equals(idVotosMax)))
				return true;
		}
		return false;
	}
	
	private static List<Cuota> generarNuevasCuotas(List<Cuota> cuotas, String nombreCuota){
		Long cantidadTotalDeVotos = 0L;
		Long cantidadDeVotosDeLaOpcionMasVotada = 0L;
		
		for (Cuota cuota : cuotas) {
			cantidadTotalDeVotos += cuota.getCantidadVotos();
			if(cuota.getNombre().equals(nombreCuota))
				cantidadDeVotosDeLaOpcionMasVotada = cuota.getCantidadVotos();
		}
		//Porcentaje de la apuesta mas votada (0.75 = 75%)
		Double p = (double) (cantidadTotalDeVotos/cantidadDeVotosDeLaOpcionMasVotada);
		
		//Rango para limitar el desplazamiento
		Double r = 1.00d;
		
		//Minimo que lo que el valor de una cuota puede llegar a valer
		Double min = 1.25d;
		
		/*Limitante para el rango de desplazamiento. 
		 * El limitante mueve aproximadamente lo valores de las cuota con estos valores si...
		 * l = 100 	(Mueve 0.02 aprox)
		 * l = 10	(Mueve 0.2 aprox)
		 * l = 1000 (Mueve 0.02 aprox)
		 * Etc..*/
		Double l = 100.00d;
		
		//Diferencia
		Double d = (r + p)/l;
		
		/*Aplicando la diferencia:
		 * - Se itera y se va viendo si la cuota es la mas votada o no. Si lo es, se le resta 
		 * al valor original la diferencia obtenida por la formula anterior (SI Y SOLO SI, 
		 * si el nuevo valor no es menor al minimo que puede tener una cuota -seteado arriba-).
		 * - Si no es la cuota mas votada, se le suma una parte de la diferencia (se divide 
		 * entre las cuotas restantes). Por ejemplo, si la diferencia es de 0.02d y hay dos 
		 * cuotas que no son las mas votadas, cada una recibe 0.01d*/
		for(int i = 0; i < cuotas.size(); i++){
			if(cuotas.get(i).getNombre().equals(nombreCuota)){
				if((cuotas.get(i).getValor() - d) >= min)
					cuotas.get(i).setValor(cuotas.get(i).getValor() - d);
			}else{
				cuotas.get(i).setValor(cuotas.get(i).getValor() + (d/(cuotas.size() - 1)));
			}
		}		
		
		//Se devuelve la nueva lista
		return cuotas;
	}
}
package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface CuotaDao {

	 void  adjust(List<Cuota> cuotas, String cuotaNombreVotada);
	 List<Cuota> findByEvent(Evento evento);
	
}

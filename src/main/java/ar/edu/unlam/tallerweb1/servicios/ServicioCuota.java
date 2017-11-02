package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface ServicioCuota { 
	List<Cuota> traerCuotasSegunEvento(Evento evento); 
	void recalcularCuotas(List<Cuota> cuotas, String cuotaNombreVotada);
}

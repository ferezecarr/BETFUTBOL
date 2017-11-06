package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Cuota;
import ar.edu.unlam.tallerweb1.modelo.Evento;

public interface ServicioCuota { 
	List<Cuota> traerCuotasSegunEvento(Evento evento); 
	List<Cuota> recalcularCuotas(List<Cuota> cuotas, String cuotaNombreVotada);
	void agregarVoto(Long eventoId, String cuotaVotada);
}

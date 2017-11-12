package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.RankingDTO;

public interface ServicioRanking {
	 List<RankingDTO> traerRankingGeneral();
	 List<RankingDTO> traerRankingGeneralPorNombreDeEvento(String filtro);
}
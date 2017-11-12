package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.RankingDao;
import ar.edu.unlam.tallerweb1.modelo.RankingDTO;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ServicioRankingImpl implements ServicioRanking {
	
	@Inject
	private RankingDao rankingServicioDao;

	@Override
	public List<RankingDTO> traerRankingGeneral() {
		return rankingServicioDao.findAll();
	}

	@Override
	public List<RankingDTO> traerRankingGeneralPorNombreDeEvento(String filtro) {
		return rankingServicioDao.findByEventName(filtro);
	}
}

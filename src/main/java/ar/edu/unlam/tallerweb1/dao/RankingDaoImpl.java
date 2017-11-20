package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.RankingDTO;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@SuppressWarnings("unchecked")
public class RankingDaoImpl implements RankingDao{	
	@Inject
	private SessionFactory sessionFactory;	

	@Override
	public List<RankingDTO> findAll() {
		String sql =		"SELECT "
						+ 		"U.nombreYApellido AS Usuario, "
						+ 		"ROUND(SUM(A.cantidadApostada * A.cuotaValor), 2) AS Ganancia "
						+ 	"FROM Usuario U "
						+ 		"JOIN Apuesta A ON U.id=A.apostador_id "
						+	"WHERE A.isGanadora IS TRUE "
						+ 	"GROUP BY U.id "
						+ 	"ORDER BY Ganancia DESC "
						+	"LIMIT 5";
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addScalar("usuario")
				.addScalar("ganancia")
				.setResultTransformer(Transformers.aliasToBean(RankingDTO.class))				
				.list();
	}

	@Override
	public List<RankingDTO> findByEventName(String filtro) {
		String sql =		"SELECT "
						+ 		"U.nombreYApellido AS Usuario, "
						+ 		"ROUND(SUM(A.cantidadApostada * A.cuotaValor), 2) AS Ganancia "
						+ 	"FROM Usuario U "
						+ 		"JOIN Apuesta A ON U.id=A.apostador_id "
						+ 		"JOIN Evento E ON A.evento_id=E.id "
						+ 	"WHERE E.nombre='" + filtro + "' "
						+	"	AND A.isGanadora IS TRUE "						
						+ 	"GROUP BY U.id "
						+ 	"ORDER BY Ganancia DESC "
						+	"LIMIT 5";
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addScalar("usuario")
				.addScalar("ganancia")
				.setResultTransformer(Transformers.aliasToBean(RankingDTO.class))				
				.list();
	}
}
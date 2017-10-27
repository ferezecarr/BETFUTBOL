package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import ar.edu.unlam.tallerweb1.SpringTest;

//Clase que prueba la conexion a la base de datos.Hereda de SpringTest por lo que corre dentro del contexto
//de spring
public class ConexionBaseDeDatosTest extends SpringTest {

	@Test
	@Transactional
	@Rollback(true)
	public void pruebaConexio() {
		assertThat(getSession().isConnected()).isTrue();
	}
}

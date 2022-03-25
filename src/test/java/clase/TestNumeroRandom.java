package clase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elorrieta.basico.PedirDatosAlumnoConEdad;

public class TestNumeroRandom {

	@Test
	public void test() {
		for (int i = 0; i < 1000000; i++) {
			int numero = PedirDatosAlumnoConEdad.generaNumeroAleatorio();			
			assertTrue ( numero >=0 && numero <= 100);			
		}		
	}

}

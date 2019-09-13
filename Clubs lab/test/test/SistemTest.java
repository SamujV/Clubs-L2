package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import interfaz.Sistema;
import model.Club;

class SistemTest {
	private Sistema sistema;

	private void setUpEscenario1() {
		try {
			sistema = new Sistema();
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}

	
	@Test
	void prueba() {
		setUpEscenario1();
		Club c = new Club("id", "name", "daf", "fa");
		
		
		
		
	}


}

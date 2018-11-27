package co.com.ceiba.CeibaParqueadero;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class CeibaParqueaderoApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void sumar() {
		int numero = 4;
		assertEquals(4,numero);
	}

}

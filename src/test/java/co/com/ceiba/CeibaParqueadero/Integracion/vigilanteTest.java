package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Vigilante;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;
import testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vigilanteTest {

	public static final String PLACA = "abc987";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para carros";
	
	@Autowired
	Vigilante vigilante;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Before
	public void setUp() {
		vehiculoRepository.eliminarTodo();
	}

	@Test
	public void registrarVehiculoTest() throws ParqueaderoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		//act
		vigilante.registrarVehiculo(vehiculo);
		Boolean existe = vehiculoRepository.existeVehiculo(vehiculo.getPlaca());
		
		//assert
		assertTrue(existe);
	}
	
	@Test 
	public void registrarVehiculoNoDisponibilidadTest() throws ParqueaderoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		//act
		try {
			for(int i = 0;i<Constants.MAX_CARROS+1;i++) {
				vehiculo.setPlaca(PLACA+i);
				vigilante.registrarVehiculo(vehiculo);
			}
			fail();
		//assert
		}catch (ParqueaderoException e) {
			assertEquals(ERROR_DISPONIBILIDAD, e.getMessage());
		}
		
		
	}

}

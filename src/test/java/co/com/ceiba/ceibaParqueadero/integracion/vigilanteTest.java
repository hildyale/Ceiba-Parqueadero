package co.com.ceiba.ceibaParqueadero.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dataBuilder.VehiculoTestDataBuilder;
import co.com.ceiba.ceibaParqueadero.dominio.Vigilante;
import co.com.ceiba.ceibaParqueadero.dominio.factory.VehiculoFactory;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.dominio.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.CarroValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vigilanteTest {

	public static final String PLACA = "bdc987";
	public static final String ERROR_DISPONIBILIDAD_CARROS = "No hay disponibilidad para carros";
	public static final String ERROR_DISPONIBILIDAD_MOTOS = "No hay disponibilidad para motos";
	
	/*
	@MockBean
	CarroValidador carroValidador;
	
	@MockBean
	VehiculoFactory vehiculoFactory;*/
	
	@Autowired
	Vigilante vigilante;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
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
	public void registrarVehiculoNoCarroNiMotoTest() throws ParqueaderoException {
		//arrange
		String tipo = "bicicleta";
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(tipo).build();
		
		//act
		try {
			vigilante.registrarVehiculo(vehiculo);
			fail();
		//assert
		}catch (ParqueaderoException e) {
			assertEquals(Constants.SOLO_CARROS_Y_MOTOS, e.getMessage());
		}
	}
	
	
	@Test 
	public void registrarCarroNoDisponibilidadTest() throws ParqueaderoException {
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
			assertEquals(ERROR_DISPONIBILIDAD_CARROS, e.getMessage());
		}
	}
	
	@Test 
	public void registrarMotoNoDisponibilidadTest() throws ParqueaderoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_MOTO).build();
		
		//act
		try {
			for(int i = 0;i<Constants.MAX_MOTOS+1;i++) {
				vehiculo.setPlaca(PLACA+i);
				vigilante.registrarVehiculo(vehiculo);
			}
			fail();
		//assert
		}catch (ParqueaderoException e) {
			assertEquals(ERROR_DISPONIBILIDAD_MOTOS, e.getMessage());
		}
		
		
	}
	
	
	@Test
	public void obtenerTodosVacio() {
		//arrange
		List<Vehiculo> vehiculos = null;
		
		//act
		vehiculos = vigilante.obtenerTodosLosVehiculos();
		
		//assert
		assertEquals(0,vehiculos.size());
	}
	
	@Test
	public void obtenerTodos() throws ParqueaderoException {
		//arrange
		List<Vehiculo> vehiculos = null;
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		//act
		vigilante.registrarVehiculo(vehiculo);
		vehiculos = vigilante.obtenerTodosLosVehiculos();
		
		//asset
		assertEquals(1,vehiculos.size());
	}

}

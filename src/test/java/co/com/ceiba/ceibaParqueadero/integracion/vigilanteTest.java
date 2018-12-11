package co.com.ceiba.ceibaParqueadero.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dataBuilder.VehiculoTestDataBuilder;
import co.com.ceiba.ceibaParqueadero.dominio.Vigilante;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
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
		Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		
		//assert
		assertEquals(vehiculo.getPlaca(),vehiculoEncontrado.getPlaca());
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
	

	@Test 
	public void salidaCarroMenosDe9HorasTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 5;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		//assert
		assertEquals(Constants.VALOR_HORA_CARRO*cantidadHorasACorrer,valor,0.01);
	}
	

	@Test 
	public void salidaMotoMenosDe9HorasTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 5;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals(Constants.VALOR_HORA_MOTO*cantidadHorasACorrer,valor,0.01);
	}
	

	@Test 
	public void salidaMotoMenosDe9HorasYCilindrajeMayorTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(600).conTipo(Constants.TIPO_MOTO).build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 5;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals((Constants.VALOR_HORA_MOTO*cantidadHorasACorrer)+Constants.RECARGO_MOTO,valor,0.01);
	}
	
	@Test 
	public void salidaDeCarroEntre9Y24HorasTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 15;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals(Constants.VALOR_DIA_CARRO,valor,0.01);
	}
	
	@Test 
	public void salidaDeMotoEntre9Y24HorasTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 15;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals(Constants.VALOR_DIA_MOTO,valor,0.01);
	}
	
	@Test 
	public void salidaDeCarroMasDeUnDiaTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 6;
		int cantidadDiasACorrer = 1;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-cantidadDiasACorrer);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals((Constants.VALOR_DIA_CARRO*cantidadDiasACorrer)+(Constants.VALOR_HORA_CARRO*cantidadHorasACorrer),valor,0.01);
	}
	
	@Test 
	public void salidaDeMotoMasDeUnDiaTest() throws ParqueaderoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehiculo.getFechaIngreso());
		int cantidadHorasACorrer = 6;
		int cantidadDiasACorrer = 1;
		
		//act
		vehiculoRepository.crearVehiculo(vehiculo);
		vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-cantidadDiasACorrer);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		vehiculoRepository.actualizarVehiculo(vehiculo);
		double valor = vigilante.salidaVehiculo(vehiculo.getPlaca());
		
		//assert
		assertEquals((Constants.VALOR_DIA_MOTO*cantidadDiasACorrer)+(Constants.VALOR_HORA_MOTO*cantidadHorasACorrer),valor,0.01);
	}
	

}

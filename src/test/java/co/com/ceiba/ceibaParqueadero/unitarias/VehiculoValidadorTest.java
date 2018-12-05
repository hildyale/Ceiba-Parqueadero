package co.com.ceiba.ceibaParqueadero.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dataBuilder.VehiculoTestDataBuilder;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.CarroValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class VehiculoValidadorTest {
	
	@Autowired
	@Qualifier("carro")
	VehiculoValidador vehiculoValidador;
	
	@Autowired
	@Qualifier("moto")
	VehiculoValidador motoValidador;
	/*
	@MockBean
	VehiculoRepository vehiculoRepository;*/
	
	/*
	@Test
	public void validarDisponibilidadCarros() {
		Long cant = (long) 18;
		when(vehiculoRepository.obtenerCantidadPorTipo("carro")).thenReturn(cant);
		try {
			carroValidador.validarDisponibilidad();
			fail();
		}catch(ParqueaderoException e) {
			assertEquals(CarroValidador.ERROR_DISPONIBILIDAD,e.getMessage());
		}
		
	}
	*/
	
	@Test
	public void obtenerDiaDeLaSemanaTest() {
		//arrange
		int hoy = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		//act
		int diaObtenido = vehiculoValidador.obtenerDiaDeLaSemana();
		
		//assert
		assertEquals(hoy,diaObtenido);
	}
	
	@Test
	public void validarLetraTest() {
		//arrange
		String placa = "abc432";
		
		//act
		Boolean esValida = vehiculoValidador.validarLetra(placa);
		
		//assert
		assertTrue(esValida);
	}
	
	@Test
	public void obtenerHorasTranscurridadTest() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Calendar calendar = Calendar.getInstance();
		Date fechaIngreso = vehiculo.getFechaIngreso();
		int horasACorrer = 50;
		
		//act
		calendar.setTime(fechaIngreso);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)+ horasACorrer);
		Date fechaModificada = calendar.getTime();
		int horasTranscurridas = vehiculoValidador.obtenerHorasTrascurridas(fechaIngreso, fechaModificada);
		
		//assert
		assertEquals(horasACorrer,horasTranscurridas);
	}
	
	@Test 
	public void obtenerValorCarroMenosDe9HorasTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadHorasACorrer = 5;
		
		//act
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = vehiculoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals(Constants.VALOR_HORA_CARRO*cantidadHorasACorrer,valor,0.01);
	}
	
	@Test 
	public void obtenerValorMotoMenosDe9HorasTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadHorasACorrer = 5;
		
		//act
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = motoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals(Constants.VALOR_HORA_MOTO*cantidadHorasACorrer,valor,0.01);
	}
	
	@Test 
	public void obtenerValorCarroEntre9Y24HorasTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadHorasACorrer = 15;
		
		//act
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = vehiculoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals(Constants.VALOR_DIA_CARRO,valor,0.01);
	}
	
	@Test 
	public void obtenerValorMotoEntre9Y24HorasTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadHorasACorrer = 15;
		
		//act
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = motoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals(Constants.VALOR_DIA_MOTO,valor,0.01);
	}
	
	@Test 
	public void obtenerMotoValorEntre9Y24HorasYCilindrajeMayorTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(600).conTipo(Constants.TIPO_MOTO).build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadHorasACorrer = 15;
		
		//act
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = motoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals(Constants.VALOR_DIA_MOTO+Constants.RECARGO_MOTO,valor,0.01);
	}
	
	@Test 
	public void obtenerValorCarroMasDeUnDiaTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadDiasACorrer = 2;
		int cantidadHorasACorrer = 5;
		
		//act
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-cantidadDiasACorrer);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = vehiculoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals((Constants.VALOR_DIA_CARRO*cantidadDiasACorrer)+(Constants.VALOR_HORA_CARRO*cantidadHorasACorrer),valor,0.01);
	}
	
	@Test 
	public void obtenerValorMotoMasDeUnDiaTest(){
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conCilindraje(400).conTipo(Constants.TIPO_MOTO).build();
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int cantidadDiasACorrer = 2;
		int cantidadHorasACorrer = 5;
		
		//act
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-cantidadDiasACorrer);
		calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR)-cantidadHorasACorrer);
		Date fechaModificada = calendar.getTime();
		vehiculo.setFechaIngreso(fechaModificada);
		double valor = motoValidador.calcularValor(vehiculo);
		
		//assert
		assertEquals((Constants.VALOR_DIA_MOTO*cantidadDiasACorrer)+(Constants.VALOR_HORA_MOTO*cantidadHorasACorrer),valor,0.01);
	}
}

package co.com.ceiba.ceibaParqueadero.unitarias;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dominio.validaciones.CarroValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class VehiculoValidadorMockTest {
	
	@MockBean
	@Qualifier("carro")
	VehiculoValidador vehiculoValidador;
	
	@Test
	public void validarPlacaDomingoTest() throws ParqueaderoException {
		//arrange
		String placa = "abc123";
		when(vehiculoValidador.obtenerDiaDeLaSemana()).thenReturn(Calendar.SUNDAY);
		
		//act
		try {
			vehiculoValidador.validarPlaca(placa);
			
		//assert
			return;
		}catch (ParqueaderoException e) {
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void validarPlacaLunesTest() throws ParqueaderoException {
		//arrange
		String placa = "abc123";
		when(vehiculoValidador.obtenerDiaDeLaSemana()).thenReturn(Calendar.MONDAY);
		
		//act
		try {
			vehiculoValidador.validarPlaca(placa);
			
		//assert
			return;
		}catch (ParqueaderoException e) {
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void validarPlacaJuevesTest() throws ParqueaderoException {
		//arrange
		String placa = "abc123";
		when(vehiculoValidador.obtenerDiaDeLaSemana()).thenReturn(Calendar.THURSDAY);
		
		//act
		try {
			vehiculoValidador.validarPlaca(placa);
			
		//assert
			return;
		}catch (ParqueaderoException e) {
			fail(e.getMessage());
		}	
	}

}

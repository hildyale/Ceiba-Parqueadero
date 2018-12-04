/*package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Vigilante;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.CarroValidador;
import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;
import testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vigilanteTestMockito {


	@MockBean
	CarroValidador vehiculoValidador;
	
	@MockBean
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	Vigilante vigilante;
	
	@Test
	public void registrarVehiculoPlacaErrorTest() throws ParqueaderoException, ParseException {
		//arrange 
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String lunes = "12/3/2018";
		Date fechaLunes = format.parse(lunes); 
		String placa = "abc963";
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(placa).build();
	
		when(vehiculoValidador.obtenerDiaDeLaSemana()).thenReturn(Calendar.THURSDAY);
		
		
		//act
		try {
			System.out.println("-------dia de la semana-----en prueba");
			System.out.println(vehiculoValidador.obtenerDiaDeLaSemana());
			System.out.println(vehiculo.getPlaca());
			vigilante.registrarVehiculo(vehiculo);
			fail();
		//assert
		}catch (ParqueaderoException e) {
			assertEquals(Constants.RESTRICCION, e.getMessage());
		}
	}

}

*/



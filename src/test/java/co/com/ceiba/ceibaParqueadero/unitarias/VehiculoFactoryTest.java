package co.com.ceiba.ceibaParqueadero.unitarias;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dataBuilder.VehiculoTestDataBuilder;
import co.com.ceiba.ceibaParqueadero.dominio.factory.VehiculoFactory;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.CarroValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class VehiculoFactoryTest {

	@Autowired
	VehiculoFactory vehiculoFactory;
	
	@Autowired
	@Qualifier("carro")
	VehiculoValidador carroValidador;
	
	@Autowired
	@Qualifier("moto")
	VehiculoValidador motoValidador;
	
	@Test
	public void obtenerCarroValidador() throws ParqueaderoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_CARRO).build();
		
		//act
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		
		//assert
		assertEquals(vehiculoValidador.getClass(),carroValidador.getClass());
	}
	
	@Test
	public void obtenerMotoValidador() throws ParqueaderoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_MOTO).build();
		
		//act
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		
		//assert
		assertEquals(vehiculoValidador.getClass(),motoValidador.getClass());
	}

}

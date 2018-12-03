package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;
import testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vehiculoRepositoryTests {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Before
	public void setUp() {
		vehiculoRepository.eliminarTodo();
	}
	
	@Test
	public void guardarYObtenerVehiculo() throws ParqueaderoException {
		
		//Arrange
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
    	Calendar calendarActual = Calendar.getInstance();
        Calendar calendarEncontrado = Calendar.getInstance();
        
        //Act
        vehiculoRepository.crearVehiculo(vehiculo);
        Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca()); 
        calendarActual.setTime(vehiculo.getFechaIngreso());
        calendarEncontrado.setTime(vehiculoEncontrado.getFechaIngreso());
        
        //Assert
        assertEquals(calendarActual.get(Calendar.DAY_OF_YEAR),calendarEncontrado.get(Calendar.DAY_OF_YEAR));
    }
	
	@Test
	public void guardarExistente() throws ParqueaderoException {
		
		//Arrange
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        
        //Act
    	try {
	        vehiculoRepository.crearVehiculo(vehiculo);
	        vehiculoRepository.crearVehiculo(vehiculo);
	        fail();
    	}catch (Exception e) {
		//Assert
    		
			assertEquals(Constants.ERROR_VEHICULO_YA_EXISTE, e.getMessage());
		}
    }
	

	@Test
	public void obtenerNoExistentePorPlaca() throws ParqueaderoException {
		
		//Arrange
    	String placa = "joj123";
    	
        //Act
    	try {
	        vehiculoRepository.obtenerVehiculoPorPlaca(placa);
	        fail();
    	}catch (Exception e) {
    		
		//Assert
			assertEquals(Constants.ERROR_VEHICULO_NO_EXISTE, e.getMessage());
		}
    }
	
	@Test
	public void obtenerNoExistente() throws ParqueaderoException {
		
		//Arrange
		String placa = "joj123";
    	Boolean existe = true;
    	
        //Act
	    existe = vehiculoRepository.existeVehiculo(placa);

		//Assert
		assertFalse(existe);
    }
	
	@Test
	public void guardarYVerificarQueExiste() throws ParqueaderoException {
		
		//Arrange
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        
        //Act
        vehiculoRepository.crearVehiculo(vehiculo);
        Boolean existe = vehiculoRepository.existeVehiculo(vehiculo.getPlaca()); 
        
        //Assert
        assertTrue(existe);
    }
	
	@Test
	public void obtenerTodosVacio() {
		
		//Arrange
		 List<Vehiculo> listarVehiculos = null;
        
        //Act
		listarVehiculos = vehiculoRepository.obtenerTodosLosVehiculos();
        int cantidadVehiculos = listarVehiculos.size();
        
        //Assert
        assertEquals(0,cantidadVehiculos);
    }
	
	@Test
	public void obtenerTodos() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		List<Vehiculo> listarVehiculos = null;
        
        //Act
		vehiculoRepository.crearVehiculo(vehiculo);
		listarVehiculos = vehiculoRepository.obtenerTodosLosVehiculos();
        int cantidadVehiculos = listarVehiculos.size();
        
        //Assert
        assertEquals(1,cantidadVehiculos);
    }
	
	@Test
	public void eliminarVehiculoExistente() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
    	
        //Act
		vehiculoRepository.crearVehiculo(vehiculo);
	    vehiculoRepository.eliminarVehiculo(vehiculo.getPlaca());

		//Assert
		return;
    }
	
	@Test
	public void eliminarVehiculoNoExistente() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
    	
        //Act
    	try {
	        vehiculoRepository.eliminarVehiculo(vehiculo.getPlaca());
    	}catch (Exception e) {
    		
		//Assert
			assertEquals(Constants.ERROR_VEHICULO_NO_EXISTE, e.getMessage());
		}
    }
	
	@Test
	public void obtenerCantidadPorCarro() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_CARRO).build();
		long cant = 0;
    	
        //Act
	    vehiculoRepository.obtenerCantidadPorTipo(Constants.TIPO_CARRO);
    		
		//Assert
			assertEquals(1, cant);
    }
	
	@Test
	public void obtenerCantidadPorMoto() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_MOTO).build();
		long cant = 0;
    	
        //Act
	    vehiculoRepository.obtenerCantidadPorTipo(Constants.TIPO_MOTO);
    		
		//Assert
			assertEquals(1, cant);
    }

}

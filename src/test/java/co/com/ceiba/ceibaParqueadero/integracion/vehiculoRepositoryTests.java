package co.com.ceiba.ceibaParqueadero.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaParqueadero.dataBuilder.VehiculoTestDataBuilder;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.util.Constants;

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
	public void guardarVehiculo() throws ParqueaderoException {
		
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
	public void actualizarVehiculo() throws ParqueaderoException {
		
		//Arrange
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        
        //Act
        vehiculoRepository.crearVehiculo(vehiculo);
        vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
        vehiculo.setCilindraje(2200);
        vehiculoRepository.actualizarVehiculo(vehiculo);
        Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca());
        
        //Assert
        assertEquals(vehiculoEncontrado.getCilindraje(),vehiculo.getCilindraje());
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
	public void guardarYVerificarQueExiste() throws ParqueaderoException {
		
		//Arrange
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        
        //Act
        vehiculoRepository.crearVehiculo(vehiculo);
        Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca(vehiculo.getPlaca()); 
        
        //Assert
        assertEquals(vehiculo.getPlaca(),vehiculoEncontrado.getPlaca());
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
	    vehiculoRepository.eliminarVehiculo(vehiculo);

		//Assert
		return;
    }
	
	@Test
	public void eliminarVehiculoNoExistente() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
    	
        //Act
    	try {
	        vehiculoRepository.eliminarVehiculo(vehiculo);
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
		vehiculoRepository.crearVehiculo(vehiculo);
	    cant = vehiculoRepository.obtenerCantidadPorTipo(Constants.TIPO_CARRO);
    		
		//Assert
			assertEquals(1, cant);
    }
	
	@Test
	public void obtenerCantidadPorMoto() throws ParqueaderoException {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(Constants.TIPO_MOTO).build();
		long cant = 0;
    	
        //Act
		vehiculoRepository.crearVehiculo(vehiculo);
	    cant = vehiculoRepository.obtenerCantidadPorTipo(Constants.TIPO_MOTO);
		//Assert
		assertEquals(1, cant);
    }

}

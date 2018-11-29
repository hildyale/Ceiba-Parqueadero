package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;
import co.com.ceiba.CeibaParqueadero.Persistencia.Repository.VehiculoEntityRepository;
import testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vehiculoRepositoryTests {

	@Autowired
    private VehiculoEntityRepository vehiculoRepository2;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	
   @Test
   public void guardarYObtenerVehiculo() throws ParqueaderoException {
    	Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        vehiculoRepository.crearVehiculo(vehiculo);
        Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca("nknk123"); 
        assertEquals("logan", vehiculoEncontrado.getClase());
    }
   
   @Test
   public void guardarYObtenerVehiculo2() throws ParqueaderoException {
   	Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("koj123").build();
       vehiculoRepository.crearVehiculo(vehiculo);
       Vehiculo vehiculoEncontrado = vehiculoRepository.obtenerVehiculoPorPlaca("nknk123"); 
       assertEquals("logan", vehiculoEncontrado.getClase());
   }

}

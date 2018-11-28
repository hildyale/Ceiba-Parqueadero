package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.CeibaParqueadero.CeibaParqueaderoApplication;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Repository.VehiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CeibaParqueaderoApplication.class)
public class vehiculoRepositoryTests {

	@Autowired
    private VehiculoRepository vehiculoRepository;
 
    @Test
    public void guardarYObtenerVehiculo() throws ParqueaderoException {
        Vehiculo vehiculo = vehiculoRepository
          .save(new Vehiculo("nknk123","carro",0,"blanco","renault","logan"));
        Vehiculo foundEntity = vehiculoRepository
          .findById(vehiculo.getPlaca())
          .orElseThrow(()-> new ParqueaderoException("resource no found"));
  
        assertNotNull(foundEntity);
        assertEquals(vehiculo.getFechaIngreso(), foundEntity.getFechaIngreso());
    }

}

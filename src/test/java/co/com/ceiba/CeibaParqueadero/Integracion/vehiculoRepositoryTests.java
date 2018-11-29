package co.com.ceiba.CeibaParqueadero.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;
import co.com.ceiba.CeibaParqueadero.Persistencia.Repository.VehiculoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class vehiculoRepositoryTests {

	@Autowired
    private VehiculoRepository vehiculoRepository;
 
    @Test
    public void guardarYObtenerVehiculo() throws ParqueaderoException {
        VehiculoEntity vehiculo = vehiculoRepository
          .save(new VehiculoEntity("nknk123","carro",0,"blanco","renault","logan"));
        VehiculoEntity foundEntity = vehiculoRepository
          .findById(vehiculo.getPlaca())
          .orElseThrow(()-> new ParqueaderoException("resource no found"));
  
        assertNotNull(foundEntity);
        assertEquals(vehiculo.getFechaIngreso(), foundEntity.getFechaIngreso());
    }

}

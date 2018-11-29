package co.com.ceiba.CeibaParqueadero.Dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Persistencia.Builder.VehiculoBuilder;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;
import co.com.ceiba.CeibaParqueadero.Persistencia.Repository.VehiculoRepository;

@Service
public class VigilanteImp implements Vigilante {

	public VigilanteImp() {
		
	}

	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public Vehiculo crearVehiculo(Vehiculo vehiculo) {
		VehiculoEntity vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
		vehiculoRepository.save(vehiculoEntity);
		return vehiculo;
	}

}

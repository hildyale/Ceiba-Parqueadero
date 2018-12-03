package co.com.ceiba.CeibaParqueadero.Persistencia.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Persistencia.Builder.VehiculoBuilder;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@Service
public class VehiculoRepositoryImp implements VehiculoRepository {

	public VehiculoRepositoryImp() {
		
	}

	@Autowired
	VehiculoEntityRepository vehiculoEntityRepository;
	
	@Override
	public void crearVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(existeVehiculo(vehiculo.getPlaca())) {
			throw new ParqueaderoException(Constants.ERROR_VEHICULO_YA_EXISTE);
		}
		vehiculoEntityRepository.save(VehiculoBuilder.convertirAEntity(vehiculo));
	}

	@Override
	public Boolean existeVehiculo(String placa) {
		Optional<VehiculoEntity> vehiculoOptional = vehiculoEntityRepository.findById(placa);
		return vehiculoOptional.isPresent();
	}
	
	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) throws ParqueaderoException {
		VehiculoEntity vehiculoEntity = vehiculoEntityRepository.findById(placa)
				.orElseThrow(() -> new ParqueaderoException(Constants.ERROR_VEHICULO_NO_EXISTE));
		
		return VehiculoBuilder.convertirADominio(vehiculoEntity);
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos(){
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		List<VehiculoEntity> vehiculosEntity = vehiculoEntityRepository.findAll();
		for(VehiculoEntity vehiculoEntity: vehiculosEntity){
			vehiculos.add(VehiculoBuilder.convertirADominio(vehiculoEntity));
		}
		return vehiculos;
	}


	@Override
	public void eliminarVehiculo(String placa) throws ParqueaderoException {
		VehiculoEntity vehiculoEntity = vehiculoEntityRepository.findById(placa)
				.orElseThrow(() -> new ParqueaderoException(Constants.ERROR_VEHICULO_NO_EXISTE));
		vehiculoEntityRepository.delete(vehiculoEntity);
	}


	@Override
	public Long obtenerCantidadPorTipo(String tipo) {
		return vehiculoEntityRepository.countByTipo(tipo);
	}
	
	@Override
	public void eliminarTodo() {
		vehiculoEntityRepository.deleteAll();
	}
	
	

}

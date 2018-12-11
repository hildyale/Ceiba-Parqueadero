package co.com.ceiba.ceibaParqueadero.persistencia.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@Service
public class VehiculoRepositoryImp implements VehiculoRepository {

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
		List<VehiculoEntity> vehiculosEntity = vehiculoEntityRepository.findAllByOrderByFechaIngresoDesc();
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
	

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		if(!existeVehiculo(vehiculo.getPlaca())) {
			throw new ParqueaderoException(Constants.ERROR_VEHICULO_NO_EXISTE);
		}
		vehiculoEntityRepository.save(VehiculoBuilder.convertirAEntity(vehiculo));
	}
	

}

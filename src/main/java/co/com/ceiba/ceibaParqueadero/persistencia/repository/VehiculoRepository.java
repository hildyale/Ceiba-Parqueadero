package co.com.ceiba.ceibaParqueadero.persistencia.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, String> {

	Long countByTipo(String tipo);
	List<VehiculoEntity> findAllByOrderByFechaIngresoDesc();
	
	public default VehiculoEntity crearVehiculo(Vehiculo vehiculo) {
		return this.save(VehiculoBuilder.convertirAEntity(vehiculo));
	}
	
	public default Vehiculo obtenerVehiculoPorPlaca(String placa) throws ParqueaderoException {
		VehiculoEntity vehiculoEntity  = this.findById(placa).orElseThrow(() -> new ParqueaderoException(Constants.ERROR_VEHICULO_NO_EXISTE));
		return VehiculoBuilder.convertirADominio(vehiculoEntity);
	}
	
	public default Long obtenerCantidadPorTipo(String tipo) {
		return this.countByTipo(tipo);
	}
	
	public default void eliminarTodo() {
		this.deleteAll();
	}
	
	public default void eliminarVehiculo(Vehiculo vehiculo){
		this.delete(VehiculoBuilder.convertirAEntity(vehiculo));
	}
	
	public default LinkedList<Vehiculo> obtenerTodosLosVehiculos(){
		LinkedList<Vehiculo> vehiculos = new LinkedList<>();
		List<VehiculoEntity> vehiculosEntity = this.findAllByOrderByFechaIngresoDesc();
		for(VehiculoEntity vehiculoEntity: vehiculosEntity){
			vehiculos.add(VehiculoBuilder.convertirADominio(vehiculoEntity));
		}
		return vehiculos;
	}
	
	public default void actualizarVehiculo(Vehiculo vehiculo) {
		this.save(VehiculoBuilder.convertirAEntity(vehiculo));
	}
	
	
}

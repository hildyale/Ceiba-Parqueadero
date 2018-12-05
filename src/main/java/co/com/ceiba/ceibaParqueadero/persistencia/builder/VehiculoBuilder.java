package co.com.ceiba.ceibaParqueadero.persistencia.builder;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;

public class VehiculoBuilder {

	private VehiculoBuilder() {}
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		
		Vehiculo vehiculo = null;
		
		if(vehiculoEntity != null) {
			vehiculo = new Vehiculo();
			vehiculo.setPlaca(vehiculoEntity.getPlaca());
			vehiculo.setTipo(vehiculoEntity.getTipo());
			vehiculo.setCilindraje(vehiculoEntity.getCilindraje());
			vehiculo.setColor(vehiculoEntity.getColor());
			vehiculo.setModelo(vehiculoEntity.getModelo());
			vehiculo.setMarca(vehiculoEntity.getMarca());
			vehiculo.setClase(vehiculoEntity.getClase());
			vehiculo.setFechaIngreso(vehiculoEntity.getFechaIngreso());
		}
		
		return vehiculo;
	}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setTipo(vehiculo.getTipo());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		vehiculoEntity.setColor(vehiculo.getColor());
		vehiculoEntity.setModelo(vehiculo.getModelo());
		vehiculoEntity.setMarca(vehiculo.getMarca());
		vehiculoEntity.setClase(vehiculo.getClase());
		vehiculoEntity.setFechaIngreso(vehiculo.getFechaIngreso());
		return vehiculoEntity;
	}

}

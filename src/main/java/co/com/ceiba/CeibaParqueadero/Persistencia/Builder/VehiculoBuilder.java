package co.com.ceiba.CeibaParqueadero.Persistencia.Builder;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;

public class VehiculoBuilder {

	private VehiculoBuilder() {}
	
	public static Vehiculo convertirADominio(VehiculoEntity vehiculoEntity) {
		
		Vehiculo vehiculo = null;
		
		if(vehiculoEntity != null) {
			vehiculo = new Vehiculo(vehiculoEntity.getPlaca(), 
									vehiculoEntity.getTipo(),
									vehiculoEntity.getCilindraje(),
									vehiculoEntity.getColor(),
									vehiculoEntity.getModelo(),
									vehiculoEntity.getMarca(),
									vehiculoEntity.getClase()							
									);
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
		return vehiculoEntity;
	}

}

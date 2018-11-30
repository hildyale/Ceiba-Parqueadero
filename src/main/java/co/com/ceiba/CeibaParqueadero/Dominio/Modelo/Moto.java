package co.com.ceiba.CeibaParqueadero.Dominio.Modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@Component
public class Moto extends VehiculoValidador {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	public static final String TIPO = "moto";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para motos";
	
	public Moto() {
	}

	@Override
	public void validarDisponibilidad() throws ParqueaderoException {
		if(vehiculoRepository.obtenerCantidadPorTipo(TIPO)>=Constants.MAX_MOTOS){
			throw new ParqueaderoException(ERROR_DISPONIBILIDAD);
		}
	}

}

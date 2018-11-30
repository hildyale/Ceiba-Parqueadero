package co.com.ceiba.CeibaParqueadero.Dominio.Modelo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;


@Component
public class Carro extends VehiculoValidador {
	
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	public static final String TIPO = "carro";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para carros";
	
	public Carro() {
	}

	@Override
	public void validarDisponibilidad() throws ParqueaderoException {
		if(vehiculoRepository.obtenerCantidadPorTipo(TIPO)>=Constants.MAX_CARROS){
			throw new ParqueaderoException(ERROR_DISPONIBILIDAD);
		}
	}

}

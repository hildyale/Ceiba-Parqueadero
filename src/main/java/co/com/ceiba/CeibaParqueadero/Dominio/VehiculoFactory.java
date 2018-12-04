package co.com.ceiba.CeibaParqueadero.Dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.CarroValidador;
import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.MotoValidador;
import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;

@Component
public class VehiculoFactory {

	private static final String SOLO_CARROS_Y_MOTOS = "Solo se permiten carros o motos";
	
	@Autowired
	CarroValidador carroValidador;
	
	@Autowired
	MotoValidador motoValidador;
	
	public VehiculoFactory() {
	}
	
	public VehiculoValidador getVehiculo(String tipo) throws ParqueaderoException {
		if(tipo.equals("carro")) {
			return carroValidador;
		}
		if(tipo.equals("moto")) {
			return motoValidador;
		}
		throw new ParqueaderoException(SOLO_CARROS_Y_MOTOS);
	}

}

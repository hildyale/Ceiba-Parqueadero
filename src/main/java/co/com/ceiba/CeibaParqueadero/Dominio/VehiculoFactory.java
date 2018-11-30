package co.com.ceiba.CeibaParqueadero.Dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Carro;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Moto;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;


@Component
public class VehiculoFactory {

	private static final String SOLO_CARROS_Y_MOTOS = "Solo se permiten carros o motos";
	@Autowired
	Carro carro;
	
	@Autowired
	Moto moto;
	
	public VehiculoFactory() {
	}
	
	public VehiculoValidador getVehiculo(String tipo) throws ParqueaderoException {
		if(tipo.equals("carro")) {
			return carro;
		}
		if(tipo.equals("moto")) {
			return moto;
		}
		throw new ParqueaderoException(SOLO_CARROS_Y_MOTOS);
	}

}

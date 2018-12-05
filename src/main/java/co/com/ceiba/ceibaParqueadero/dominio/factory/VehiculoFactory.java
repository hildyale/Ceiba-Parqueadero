package co.com.ceiba.ceibaParqueadero.dominio.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaParqueadero.dominio.validaciones.CarroValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.MotoValidador;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@Component
public class VehiculoFactory {
	
	@Autowired
	CarroValidador carroValidador;
	
	@Autowired
	MotoValidador motoValidador;
	
	public VehiculoValidador getVehiculoValidador(String tipo) throws ParqueaderoException {
		if(tipo.equals(Constants.TIPO_CARRO)) {
			return carroValidador;
		}
		else{
			return motoValidador;
		}
	}

}

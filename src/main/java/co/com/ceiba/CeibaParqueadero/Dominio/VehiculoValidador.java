package co.com.ceiba.CeibaParqueadero.Dominio;


import java.util.Calendar;

import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

public abstract class VehiculoValidador {
    
	public VehiculoValidador() {
	}

	public abstract void validarDisponibilidad() throws ParqueaderoException ;
	
	public void validarPlaca(String placa) throws ParqueaderoException {
		String primeraLetra = String.valueOf(placa.charAt(0));
		if(validarLetra(primeraLetra)) {
			if(obtenerDiaDeLaSemana() != Calendar.MONDAY && obtenerDiaDeLaSemana() != Calendar.SUNDAY) {
				throw new ParqueaderoException(Constants.RESTRICCION);
			}
		}
	}
	
	private boolean validarLetra(String primeraLetra) {
		return primeraLetra.equals(Constants.RESTRICCION_PRIMERA_LETRA);
	}
	
	private int obtenerDiaDeLaSemana() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	
}

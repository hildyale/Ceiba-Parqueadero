package co.com.ceiba.ceibaParqueadero.dominio.validaciones;

import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

public interface VehiculoValidador {
    
	public abstract void validarDisponibilidad() throws ParqueaderoException;
	public abstract double calcularValor(Vehiculo vehiculo);
	
	public default void validarPlaca(String placa) throws ParqueaderoException {
		if(validarLetra(placa) && noEsLunesODomingo()) {
				throw new ParqueaderoException(Constants.RESTRICCION);
		}
	}
	
	public default boolean validarLetra(String placa) {
		String primeraLetra = String.valueOf(placa.charAt(0));
		return primeraLetra.equals(Constants.RESTRICCION_PRIMERA_LETRA);
	}
	
	public default int obtenerDiaDeLaSemana() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	public default Boolean noEsLunesODomingo() {
		return obtenerDiaDeLaSemana() != Calendar.MONDAY && obtenerDiaDeLaSemana() != Calendar.SUNDAY;
	}
	
	public default Date obtenerFecha() {
		return new Date();
	}
	
	public default int obtenerHorasTrascurridas(Date fechaInicial,Date fechaFinal) {
		long secs = (fechaFinal.getTime() - fechaInicial.getTime()) / 1000;
		int hours = (int) (secs / 3600);    
		secs = secs % 3600;
		int mins = (int) (secs / 60);
		if(mins>0) {
			hours++;
		}
		//secs = secs % 60;
		return hours;
	}
	
}

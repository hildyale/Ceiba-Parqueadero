package co.com.ceiba.CeibaParqueadero.Dominio.Validaciones;


import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

public abstract class VehiculoValidador {
    
	public VehiculoValidador() {
	}

	public abstract void validarDisponibilidad() throws ParqueaderoException;
	public abstract double calcularValor(Vehiculo vehiculo);
	
	public void validarPlaca(String placa) throws ParqueaderoException {
		String primeraLetra = String.valueOf(placa.charAt(0));
		primeraLetra = primeraLetra.toLowerCase();
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
	
	public Date obtenerFecha() {
		return new Date();
	}
	
	public int obtenerHorasTrascurridas(Date fechaInicial,Date fechaFinal) {
		long secs = (fechaFinal.getTime() - fechaInicial.getTime()) / 1000;
		int hours = (int) (secs / 3600);    
		secs = secs % 3600;
		/*int mins = secs / 60;*/
		secs = secs % 60;
		System.out.println("--------------------------------horas transcurridas-----------------------------");
		System.out.println(hours);
		return hours;
		
	}
	
}

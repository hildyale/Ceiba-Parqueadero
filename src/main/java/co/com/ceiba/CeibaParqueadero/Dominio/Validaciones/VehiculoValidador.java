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
		System.out.println("validar placa");
		System.out.println("-------dia de la semana-----en validarplaca");
		System.out.println(obtenerDiaDeLaSemana());
		if(validarLetra(placa) && noEsLunesODomingo()) {
				throw new ParqueaderoException(Constants.RESTRICCION);
		}
	}
	
	public boolean validarLetra(String placa) {
		String primeraLetra = String.valueOf(placa.charAt(0));
		return primeraLetra.equals(Constants.RESTRICCION_PRIMERA_LETRA);
	}
	
	public int obtenerDiaDeLaSemana() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	public Boolean noEsLunesODomingo() {
		return obtenerDiaDeLaSemana() != Calendar.MONDAY && obtenerDiaDeLaSemana() != Calendar.SUNDAY;
	}
	
	public Date obtenerFecha() {
		return new Date();
	}
	
	public int obtenerHorasTrascurridas(Date fechaInicial,Date fechaFinal) {
		long secs = (fechaFinal.getTime() - fechaInicial.getTime()) / 1000;
		int hours = (int) (secs / 3600);    
		secs = secs % 3600;
		int mins = (int) (secs / 60);
		if(mins>0) {
			hours++;
		}
		secs = secs % 60;
		System.out.println("--------------------------------horas transcurridas-----------------------------");
		System.out.println(hours);
		return hours;
	}
	
}

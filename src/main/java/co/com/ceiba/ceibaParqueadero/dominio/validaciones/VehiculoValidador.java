package co.com.ceiba.ceibaParqueadero.dominio.validaciones;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.util.Constants;

public abstract class VehiculoValidador {
	
	@Autowired
	VehiculoRepository vehiculoEntityRepository;
    
	public abstract void validarDisponibilidad() throws ParqueaderoException;
	public abstract double calcularValor(Vehiculo vehiculo);
	
	public void validarExisteVehiculo(String placa) throws ParqueaderoException {
		Optional<VehiculoEntity> vehiculoOptional = vehiculoEntityRepository.findById(placa);
		if(vehiculoOptional.isPresent()) {
			throw new ParqueaderoException(Constants.ERROR_VEHICULO_YA_EXISTE);
		}
	}
	
	public void validarVehiculo(Vehiculo vehiculo) throws ParqueaderoException{
		this.validarDisponibilidad();
		this.validarPlaca(vehiculo.getPlaca());
		this.validarExisteVehiculo(vehiculo.getPlaca());
	}
	
	public void validarPlaca(String placa) throws ParqueaderoException {
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
			return hours;
		}
		secs = secs % 60;
		if(secs>0 && hours==0) {
			hours++;
			return hours;
		}
		return hours;
	}
	
}

package co.com.ceiba.ceibaParqueadero.dominio;

import java.text.ParseException;
import java.util.List;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;

public interface Vigilante {

	public void registrarVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	public double salidaVehiculo(String placa) throws ParqueaderoException;
	public List<Vehiculo> obtenerTodosLosVehiculos();
	
}

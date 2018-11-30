package co.com.ceiba.CeibaParqueadero.Dominio;

import java.util.List;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;

public interface Vigilante {

	public void registrarVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	public Long salidaVehiculo(String placa);
	public Vehiculo obtenerVehiculoPorPlaca(String placa)  throws ParqueaderoException;
	public List<Vehiculo> obtenerTodosLosVehiculos();
	
}
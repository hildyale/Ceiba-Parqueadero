package co.com.ceiba.ceibaParqueadero.dominio.repository;

import java.util.List;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;


public interface VehiculoRepository {
	
	public void crearVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	public Vehiculo obtenerVehiculoPorPlaca(String placa)  throws ParqueaderoException;
	public List<Vehiculo> obtenerTodosLosVehiculos();
	public void eliminarVehiculo(String placa) throws ParqueaderoException;
	public Long obtenerCantidadPorTipo(String tipo);
	public void eliminarTodo();
	public Boolean existeVehiculo(String placa);
	void actualizarVehiculo(Vehiculo vehiculo) throws ParqueaderoException;

}

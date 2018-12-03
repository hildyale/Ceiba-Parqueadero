package co.com.ceiba.CeibaParqueadero.Dominio;

import java.util.List;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.VehiculoEntity;


public interface VehiculoRepository {
	
	public void crearVehiculo(Vehiculo vehiculo) throws ParqueaderoException;
	public Vehiculo obtenerVehiculoPorPlaca(String placa)  throws ParqueaderoException;
	public List<Vehiculo> obtenerTodosLosVehiculos();
	public void eliminarVehiculo(String placa) throws ParqueaderoException;
	public Long obtenerCantidadPorTipo(String tipo);
	public void eliminarTodo();
	public Boolean existeVehiculo(String placa);

}

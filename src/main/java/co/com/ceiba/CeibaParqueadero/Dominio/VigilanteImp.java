package co.com.ceiba.CeibaParqueadero.Dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Dominio.Validaciones.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;

@Service
public class VigilanteImp implements Vigilante {

    
	public VigilanteImp() {
		
	}
	
	@Autowired
	VehiculoFactory vehiculoFactory;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculo(vehiculo.getTipo());
		vehiculoValidador.validarDisponibilidad();
		vehiculoValidador.validarPlaca(vehiculo.getPlaca());
		vehiculoRepository.crearVehiculo(vehiculo);
	}

	@Override
	public double salidaVehiculo(String placa) throws ParqueaderoException {
		System.out.println("------------------------------vigilante-----------------------------");
		Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(placa);
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculo(vehiculo.getTipo());
		double valor = vehiculoValidador.calcularValor(vehiculo);
		vehiculoRepository.eliminarVehiculo(placa);
		System.out.println("--------------------------------Valor en vigilante-----------------------------");
		System.out.println(valor);
		return valor;
	}


	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
		return vehiculoRepository.obtenerTodosLosVehiculos();
	}

}

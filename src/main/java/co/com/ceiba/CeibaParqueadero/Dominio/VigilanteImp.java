package co.com.ceiba.CeibaParqueadero.Dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
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
	public Long salidaVehiculo(String placa) {
		
		return null;
	}

	@Override
	public Vehiculo obtenerVehiculoPorPlaca(String placa) throws ParqueaderoException {
		
		return null;
	}

	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos() {
		
		return null;
	}

}

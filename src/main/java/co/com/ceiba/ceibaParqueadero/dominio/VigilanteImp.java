package co.com.ceiba.ceibaParqueadero.dominio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaParqueadero.dominio.factory.VehiculoFactory;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@Service
public class VigilanteImp implements Vigilante {
	
	@Autowired
	VehiculoFactory vehiculoFactory;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		String tipo = vehiculo.getTipo();
		if(!tipo.equals(Constants.TIPO_CARRO) && !tipo.equals(Constants.TIPO_MOTO)) {
			throw new ParqueaderoException(Constants.SOLO_CARROS_Y_MOTOS);
		}
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		vehiculoValidador.validarDisponibilidad();
		vehiculoValidador.validarPlaca(vehiculo.getPlaca());
		vehiculoRepository.crearVehiculo(vehiculo);
	}

	@Override
	public double salidaVehiculo(String placa) throws ParqueaderoException {
		Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(placa);
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		double valor = vehiculoValidador.calcularValor(vehiculo);
		vehiculoRepository.eliminarVehiculo(placa);
		return valor;
	}


	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos(){
		return vehiculoRepository.obtenerTodosLosVehiculos();
	}

}

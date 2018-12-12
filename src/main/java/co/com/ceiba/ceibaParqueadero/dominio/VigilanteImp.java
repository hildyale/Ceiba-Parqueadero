package co.com.ceiba.ceibaParqueadero.dominio;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaParqueadero.dominio.factory.VehiculoFactory;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.dominio.validaciones.VehiculoValidador;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.ceibaParqueadero.persistencia.entity.VehiculoEntity;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.util.Constants;
import co.com.ceiba.ceibaParqueadero.util.Tipos;

@Service
public class VigilanteImp implements Vigilante {
	
	@Autowired
	VehiculoFactory vehiculoFactory;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Override
	public void registrarVehiculo(Vehiculo vehiculo) throws ParqueaderoException {
		validarTipo(vehiculo.getTipo());		
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		vehiculoValidador.validarVehiculo(vehiculo);
		vehiculoRepository.crearVehiculo(vehiculo);
	}

	@Override
	public double salidaVehiculo(String placa) throws ParqueaderoException {
		Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorPlaca(placa);
		VehiculoValidador vehiculoValidador = vehiculoFactory.getVehiculoValidador(vehiculo.getTipo());
		double valor = vehiculoValidador.calcularValor(vehiculo);
		vehiculoRepository.eliminarVehiculo(vehiculo);
		return valor;
	}


	@Override
	public List<Vehiculo> obtenerTodosLosVehiculos(){
		return vehiculoRepository.obtenerTodosLosVehiculos();
	}
	
	public void validarTipo(String tipo) throws ParqueaderoException {
		tipo = tipo.toLowerCase();
		if(!Tipos.contains(tipo)) {
			throw new ParqueaderoException(Constants.SOLO_CARROS_Y_MOTOS);
		}
	}

}

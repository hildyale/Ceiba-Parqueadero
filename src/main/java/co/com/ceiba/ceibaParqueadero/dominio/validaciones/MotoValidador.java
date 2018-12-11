package co.com.ceiba.ceibaParqueadero.dominio.validaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@Component(value = "moto")
public class MotoValidador extends VehiculoValidador {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	public static final String TIPO = "moto";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para motos";
	private static final Logger logger = LoggerFactory.getLogger(MotoValidador.class);

	@Override
	public void validarDisponibilidad() throws ParqueaderoException {
		if(vehiculoRepository.obtenerCantidadPorTipo(TIPO)>=Constants.MAX_MOTOS){
			throw new ParqueaderoException(ERROR_DISPONIBILIDAD);
		}
	}

	@Override
	public double calcularValor(Vehiculo vehiculo) {
		double valor = 0;
		if(vehiculo.getCilindraje()>Constants.RESTRICCION_CILINDRAJE_MOTO) {
			valor = valor + 2000;
		}
		
		String fechaIngreso = vehiculo.getFechaIngreso().toString();
		String fechaActual = obtenerFecha().toString();
		logger.debug("----------------------Antes de la fecha---------------------------);");
		logger.debug(fechaIngreso);
		logger.debug(fechaActual);
		
		int horas = obtenerHorasTrascurridas(vehiculo.getFechaIngreso(), obtenerFecha());
		if(horas<Constants.MIN_HORAS_DIA) {
			return valor + (Constants.VALOR_HORA_MOTO*horas);
		}else if(horas < Constants.MAX_HORAS_DIA) {
			return valor + Constants.VALOR_DIA_MOTO;
		}else {
			int dias = (horas / Constants.MAX_HORAS_DIA);
			horas = horas % Constants.MAX_HORAS_DIA;
			if(horas<Constants.MIN_HORAS_DIA) {
				valor = valor + (horas*Constants.VALOR_HORA_MOTO);
			}else {
				valor = valor + Constants.VALOR_DIA_MOTO;
			}
			logger.debug("--------------------------------Valor en moto-----------------------------");
			valor = valor + (dias*Constants.VALOR_DIA_MOTO);
			String log = String.valueOf(valor);
			logger.debug(log);
			return valor;
		}
	}

}

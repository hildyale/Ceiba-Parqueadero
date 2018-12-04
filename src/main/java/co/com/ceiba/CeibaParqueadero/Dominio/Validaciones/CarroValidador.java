package co.com.ceiba.CeibaParqueadero.Dominio.Validaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;


@Component(value = "carro")
public class CarroValidador extends VehiculoValidador {
	
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	public static final String TIPO = "carro";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para carros";
	private static final Logger logger = LoggerFactory.getLogger(CarroValidador.class);

	@Override
	public void validarDisponibilidad() throws ParqueaderoException {
		if(vehiculoRepository.obtenerCantidadPorTipo(TIPO)>=Constants.MAX_CARROS){
			throw new ParqueaderoException(ERROR_DISPONIBILIDAD);
		}
	}

	@Override
	public double calcularValor(Vehiculo vehiculo) {
		double valor = 0;
		
		logger.debug("----------------------Antes de la fecha---------------------------);");
		logger.debug(vehiculo.getFechaIngreso().toString());
		logger.debug(obtenerFecha().toString());
		
		int horas = obtenerHorasTrascurridas(vehiculo.getFechaIngreso(), obtenerFecha());
		if(horas<Constants.MIN_HORAS_DIA) {
			return valor + (Constants.VALOR_HORA_CARRO*horas);
		}else if(horas < Constants.MAX_HORAS_DIA) {
			return valor + Constants.VALOR_DIA_CARRO;
		}else {
			int dias = (horas / Constants.MAX_HORAS_DIA);
			horas = horas % Constants.MAX_HORAS_DIA;
			if(horas<Constants.MIN_HORAS_DIA) {
				valor = valor + (horas*Constants.VALOR_HORA_CARRO);
			}else {
				valor = valor + Constants.VALOR_DIA_CARRO;
			}
			
			logger.debug("--------------------------------Valor en carro-----------------------------");
			valor = valor + (dias*Constants.VALOR_DIA_CARRO);
			String log = String.valueOf(valor);
			logger.debug(log);
			return valor;
		}
	}

}

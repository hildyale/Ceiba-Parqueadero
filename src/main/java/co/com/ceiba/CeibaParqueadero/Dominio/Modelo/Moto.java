package co.com.ceiba.CeibaParqueadero.Dominio.Modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoValidador;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@Component
public class Moto extends VehiculoValidador {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	public static final String TIPO = "moto";
	public static final String ERROR_DISPONIBILIDAD = "No hay disponibilidad para motos";
	
	public Moto() {
	}

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
		System.out.println("----------------------Antes de la fecha---------------------------");
		System.out.println(vehiculo.getFechaIngreso());
		System.out.println(obtenerFecha());
		int horas = obtenerHorasTrascurridas(vehiculo.getFechaIngreso(), obtenerFecha());
		if(horas<9) {
			return valor + (Constants.VALOR_HORA_MOTO*horas);
		}else if(horas < 24) {
			return valor + Constants.VALOR_DIA_MOTO;
		}else {
			int dias = (int) Math.floor(horas / 24);
			horas = horas % 24;
			if(horas<9) {
				valor = valor + (horas*Constants.VALOR_HORA_MOTO);
			}else {
				valor = valor + Constants.VALOR_DIA_MOTO;
			}
			System.out.println("--------------------------------Valor en moto-----------------------------");
			System.out.println(valor + dias*Constants.VALOR_DIA_MOTO);
			return valor + dias*Constants.VALOR_DIA_MOTO;
		}
	}

}

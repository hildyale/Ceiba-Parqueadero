package testdatabuilder;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.util.Date;

import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;

public class VehiculoTestDataBuilder {

	private static final String PLACA  = "nknk123"; 
	private static final String TIPO  = "carro";
	private static final int CILINDRAJE  = 1600;
	private static final String COLOR  = "blanco";
	private static final String MARCA  = "renault";
	private static final String CLASE  = "logan";
	private static final String MODELO  = "2018";
	private static final Date FECHA_INGRESO  = new Date();
	
	private String placa; 
	private String tipo;
	private int cilindraje;
	private String color;
	private String marca;
	private String clase;
	private Date fechaIngreso;
	private String modelo;
	
	
	public VehiculoTestDataBuilder() {
		this.placa = PLACA;
		this.tipo = TIPO;
		this.cilindraje = CILINDRAJE;
		this.color = COLOR;
		this.marca = MARCA;
		this.clase = CLASE;
		this.fechaIngreso = FECHA_INGRESO;
		this.modelo = MODELO;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa){
		this.placa = placa;
		return this;	
	}
	
	public VehiculoTestDataBuilder conTipo(String placa){
		this.tipo = tipo;
		return this;	
	}
	
	public VehiculoTestDataBuilder conCilindraje(int cilindraje){
		this.cilindraje = cilindraje;
		return this;	
	}
	
	public VehiculoTestDataBuilder conColor(String color){
		this.color = color;
		return this;	
	}
	
	public VehiculoTestDataBuilder conMarca(String marca){
		this.marca = marca;
		return this;	
	}
	
	public VehiculoTestDataBuilder conClase(String clase){
		this.clase = clase;
		return this;	
	}
	
	public VehiculoTestDataBuilder conModelo(String modelo){
		this.modelo = modelo;
		return this;	
	}
	
	public VehiculoTestDataBuilder conFecha(Date fechaIngreso){
		this.fechaIngreso = fechaIngreso;
		return this;	
	}
	
	public Vehiculo build() {
		return new Vehiculo(this.placa, this.tipo,this.cilindraje,this.color,this.modelo,this.marca,this.clase,this.fechaIngreso);
	}

}

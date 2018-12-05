package co.com.ceiba.ceibaParqueadero.dominio.modelo;

import java.util.Date;

public class Vehiculo{
	
	private String placa;
	private String tipo; 
	private int cilindraje;
	private String color;
	private String modelo;
	private String marca;
	private String clase;
	private Date fechaIngreso;

	
	
	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getCilindraje() {
		return cilindraje;
	}



	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public String getMarca() {
		return marca;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public String getClase() {
		return clase;
	}



	public void setClase(String clase) {
		this.clase = clase;
	}



	public Date getFechaIngreso() {
		return fechaIngreso;
	}



	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}

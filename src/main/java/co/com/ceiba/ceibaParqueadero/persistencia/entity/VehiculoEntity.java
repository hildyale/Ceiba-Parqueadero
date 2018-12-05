package co.com.ceiba.ceibaParqueadero.persistencia.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "vehiculo")
@EntityListeners(AuditingEntityListener.class)
public class VehiculoEntity {
	
	@Id
	private String placa;
	
	@NotBlank
	@Column(nullable = false)
	private String tipo; 
	
	@Column(nullable = false)
	private int cilindraje;
	
	@NotBlank
	@Column(nullable = false)
	private String color;
	
	
	private String modelo;
	
	@NotBlank
	@Column(nullable = false)
	private String marca;
	
	@NotBlank
	@Column(nullable = false)
	private String clase;
	
	@CreatedDate
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;
	
	public VehiculoEntity() {
		
	}

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

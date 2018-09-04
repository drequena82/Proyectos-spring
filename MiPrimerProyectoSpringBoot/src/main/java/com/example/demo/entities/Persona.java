package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Persona {
	
	public Persona() {
		
	}
	
	@Id private long id;
	private String nombre;
	private Double estatura;
	private Integer edad;
	private Date fecha;
	
	public long getIdentificador() {
		return id;
	}
	public void setIdentificador(long identificador) {
		this.id = identificador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getEstatura() {
		return estatura;
	}
	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}

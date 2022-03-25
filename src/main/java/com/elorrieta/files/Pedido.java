package com.elorrieta.files;

import java.io.Serializable;

public class Pedido implements Serializable {

	private int id;
	private String nombre;
	private int cantidad;

	public Pedido() {
		super();
		this.id = 0;
		this.nombre = "";
		this.cantidad = 0;
	}

	public Pedido(String nombre, int cantidad) {
		this();
		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}

}

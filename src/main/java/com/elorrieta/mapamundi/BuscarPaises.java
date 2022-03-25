package com.elorrieta.mapamundi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Buscamos paises preguntando al usuario
 * @author infauraga
 *
 */
public class BuscarPaises {

	public static void main(String[] args) {

		try {
		
			//TODO pedir los datos al usuario por teclado
			String paisAbuscar = "c";
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mapamundi?useSSL=false", "root", "root");
			String sql = "SELECT pais_id, nombre FROM pais WHERE UPPER(nombre) LIKE UPPER( ? ) ORDER BY nombre ASC ;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			// sustituir los interrogantes, por lo que quiere el usuario
			pst.setString(1, "%" + paisAbuscar + "%");
						
			// ejecutar la SQL
			ResultSet rs = pst.executeQuery();		
			System.out.println("Los paises que coinciden con " + paisAbuscar + " son:");
			while( rs.next() ) {
								
				String nombre = rs.getString("nombre");						
				System.out.println( nombre);
				
			}// while
			
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

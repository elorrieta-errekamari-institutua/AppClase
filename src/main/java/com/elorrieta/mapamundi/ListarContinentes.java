package com.elorrieta.mapamundi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListarContinentes {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try {
		
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mapamundi?useSSL=false", "root", "root");
			String sql = "SELECT continente_id, nombre FROM continente ORDER BY nombre ASC;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while( rs.next() ) {
				
				int id = rs.getInt("continente_id");
				String nombre = rs.getString("nombre");
						
				System.out.println( id + " " + nombre);
				
			}// while
			
			System.out.println("Esto no lo vais a ver");
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

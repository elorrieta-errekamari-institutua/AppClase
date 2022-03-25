package com.elorrieta.mapamundi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListarContinentesYnumeroPaises {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try {
		
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mapamundi?useSSL=false", "root", "root");
			String sql = "SELECT "
						+ "	c.nombre `continente`, "
						+ "	COUNT(p.continente_id) `num_paises` "
						+ "FROM continente c, pais p "
						+ "WHERE c.continente_id = p.continente_id "
						+ "GROUP BY c.continente_id "
						+ "ORDER BY num_paises DESC;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while( rs.next() ) {
				
				int numero = rs.getInt("num_paises");
				String nombre = rs.getString("continente");
						
				System.out.printf( "El continente %-7s tiene %s paises \n",nombre, numero);
				
			}// while
			
			System.out.println("Esto no lo vais a ver");
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

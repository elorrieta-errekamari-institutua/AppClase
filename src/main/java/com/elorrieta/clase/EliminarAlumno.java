package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EliminarAlumno {

	public static void main(String[] args) {

				
		try {
		
			//TODO pedir datos por pantalla
			int idAlumno = 2;
		
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
			String sql = "DELETE FROM alumno WHERE id_alumno = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);
					
			pst.setInt(1, idAlumno);			
			
			pst.executeUpdate(); 
			
			System.out.println("Alumno Eliminado");
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

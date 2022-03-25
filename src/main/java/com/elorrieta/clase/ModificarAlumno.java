package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModificarAlumno {

	public static void main(String[] args) {

				
		try {
		
			//TODO pedir datos por pantalla
			int idAlumno = 2;
			String email = "ibai2222@gmail.com";
			String pass = "1234567890";
			
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
			String sql = "UPDATE alumno SET email = ? , pass= ? WHERE id_alumno = ?;";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			pst.setString(1, email);
			pst.setString(2, pass);
			pst.setInt(3, idAlumno);
			
			
			pst.executeUpdate(); 
			
			System.out.println("Alumno modificado");
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

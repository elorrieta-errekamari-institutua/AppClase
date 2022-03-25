package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Programa para insertar un alumno en la base datos.
 * El nombre del alumno debe estar comprendido entre 1 y 45 letras
 * El email puede que este duplicado y lance una Exception, capturar y solucionar error
 * Comprobar que la contraseña sea de entre 8 y 32.
 * 
 * @author infauraga
 *
 */
public class InsertarAlumno {

	public static void main(String[] args) throws SQLException {
		
		
		//TODO capturar la excepcion del email unico y pedir uno nuevo al usuario
		
		// Obtener conexion a bbdd
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase", "root", "root");
		
		String sql  = "INSERT INTO `alumno` (`nombre`, `email`, `pass`) VALUES ( ? , ? , ?);";
		
		
		// Pedir datos por teclado y guardar en variables
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime el nombre");
		String nombre = sc.nextLine();
		
		System.out.println("Dime el email");
		String email = sc.nextLine();
		
		System.out.println("Dime la contraseña");
		String pass = sc.nextLine();
		
		// Objeto para poder interactuar desde java con la bbdd
		PreparedStatement pst = conexion.prepareStatement(sql);
		
		// sustituyo los interrogantes de la sql que contiene el pst
		pst.setString( 1, nombre);
		pst.setString( 2, email);
		pst.setString( 3, pass);
		
		// pulsamos el rayo del workbench
		int affectedRows = pst.executeUpdate();
		
		System.out.println( "filas insertadas " + affectedRows);
		
		System.out.println("Termina programa");
		
		
		
		
		
		
		
		

	}

}

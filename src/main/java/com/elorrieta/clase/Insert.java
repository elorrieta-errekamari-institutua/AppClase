package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Insert {

	/**
	 * Pide por pantalla los datos de un alumno y lo inserta en la bbdd
	 */
	public static void insertar() {

		Scanner sc = new Scanner(System.in);
		
		try (Connection con = Conexion.getConnection(); PreparedStatement pst = con.prepareStatement(App.SQL_INSERTALUMNO);

		) {

			System.out.println("Introduce el nombre");
			String nombre = sc.nextLine();

			System.out.println("Introduce el gmail");
			String email = sc.nextLine();

			// TODO validar campos y capturar excepcion de email capturado

			pst.setString(1, nombre);
			pst.setString(2, email);

			pst.executeUpdate();
			System.out.println("Alumno insertado");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// insertar
	
}

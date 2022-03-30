package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {

	/**
	 * Pide por pantalla los datos de un alumno y lo inserta en la bbdd
	 * 
	 * @param sc2
	 */
	public void insertar(Scanner sc) {
		boolean encontrado = false;

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement("INSERT INTO alumno (nombre, email) VALUES (?,?)");

		) {

			System.out.println("Introduce el nombre");
			String nombre = sc.nextLine();

			System.out.println("Introduce el gmail");
			String email = sc.nextLine();

			encontrado = new Select().buscarEmail(email);
			if (encontrado) {
				System.out.println("El email del alumno ya se encuentra en la BBDD");
			} else {
				pst.setString(1, nombre);
				pst.setString(2, email);

				pst.executeUpdate();
				System.out.println("Alumno insertado\n");
			}

		} catch (SQLException sqle) {
			System.out.println("Este email ya esta registrado, Vamos al menu.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// insertar

}

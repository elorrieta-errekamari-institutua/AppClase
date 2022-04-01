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

		String nombre = "";
		String email = "";
		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement("INSERT INTO alumno (nombre, email) VALUES (?,?)");

		) {

			// nombre vacio
			boolean nombreVacio = true;

			do {

				System.out.println("Introduce el nombre");
				nombre = sc.nextLine().trim();

				if ("".equalsIgnoreCase(nombre) || nombre.length() > 45) {

					System.out.println("El nombre no puede estar vacio o superar 45 caracteres");
				}

				else {

					pst.setString(1, nombre);
					nombreVacio = false;
				}

			} while (nombreVacio);

			// email vacio
			boolean emailVacio = true;

			do {

				System.out.println("Introduce el email");
				email = sc.nextLine().trim();

				if ("".equalsIgnoreCase(email) || email.length() > 45) {

					System.out.println("El email no puede estar vacio o superar 45 caracteres");
				}

				else {

					pst.setString(2, email);
					emailVacio = false;
				}

			} while (emailVacio);

			//si el email ya existe
			encontrado = new Select().buscarEmail(email);
			if (encontrado) {
				System.out.println("El email del alumno ya se encuentra en la BBDD. Volvemos al menu");
			} else {
				pst.setString(1, nombre);
				pst.setString(2, email);

				pst.executeUpdate();
				System.out.println("Alumno insertado\n");
			}

		} catch (SQLException sqle) {
			System.out.println("Este email ya esta registrado, Volvemos al menu.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// insertar

}

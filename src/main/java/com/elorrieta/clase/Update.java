package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Update {

	public void modificar() {

		//declaracion de variables
		String nombre;
		String email;
		int id = 0;
		Scanner sc = new Scanner(System.in);

		//sentencia sql que necesitamos
		String sql = "UPDATE alumno SET nombre = ?, email = ? WHERE id_alumno = ?;";

		//conectar con la bbdd
		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			System.out.println("Introduce la id del alumno que deseas modificar");
			//gestion de error si mete letras en vez de numeros
			boolean volverApedir = true;
			while (volverApedir) {
				try {
					id = Integer.parseInt(sc.nextLine());
					volverApedir = false;
				} catch (Exception e) {
					System.out.println("Debes introducir una id de un alumno existente, intentalo de nuevo");
					System.out.println("Introduce la id del alumno que deseas modificar");
				}
			}
			//TODO falta sacar un mensaje si la id que introduce no existe en la bbdd
			

			System.out.println("Introduce un nombre");
			nombre = sc.nextLine();

			System.out.println("Introduce un email");
			email = sc.nextLine();

			// asignar datos introducidos a los interrogantes
			pst.setString(1, nombre);
			pst.setString(2, email);
			pst.setInt(3, id);

			// ejecutar la select
			pst.executeUpdate();
			System.out.println("Alumno Actualizado");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sc.close();

	}

}

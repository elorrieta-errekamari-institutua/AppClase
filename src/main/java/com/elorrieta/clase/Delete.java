package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Delete {

	public void borrarAlumno() {

		// creracion de las variables que necesitamos para recoger los datos
		int id = 0;
		Scanner sc = new Scanner(System.in);

		// declarar la sentencia que queremos
		String sql = "DELETE FROM alumno WHERE id_alumno = ?;";

		// conectar con la bbdd
		try (Connection con = Conexion.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			System.out.println("Introduce la id del alumno que deseas eliminar");
			// gestion de error si mete letras en vez de numeros
			boolean volverApedir = true;
			while (volverApedir) {
				try {
					id = Integer.parseInt(sc.nextLine());
					volverApedir = false;
				} catch (Exception e) {
					System.out.println("Debes introducir una id de un alumno existente, intentalo de nuevo");
					System.out.println("Introduce la id del alumno que deseas eliminar");
				}
			}
			
			// asignar datos introducidos a los interrogantes
			pst.setInt(1, id);
			// pulsamos el rayo del workbench para ejecutar la sentencia
			pst.executeUpdate();
			System.out.println("Alumno eliminado correctamente");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//cerrar el teclado
		sc.close();

	}

}

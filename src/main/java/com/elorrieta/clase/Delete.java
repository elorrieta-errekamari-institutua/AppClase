package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Delete {

	public void borrarAlumno(Scanner sc) {

		// creracion de las variables que necesitamos para recoger los datos
		int id = 0;
		boolean encontrado = false;

		// declarar la sentencia que queremos
		String sql = "DELETE FROM alumno WHERE id_alumno = ?;";

		// conectar con la bbdd
		try (Connection con = Conexion.getConnection(); 
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			
			boolean volverApedir = true;
			while (volverApedir) {
				System.out.println("Introduce la id del alumno que deseas eliminar");
				try {
					id = Integer.parseInt(sc.nextLine());
					volverApedir = false;
				} catch (Exception e) {
					System.out.println("Debes introducir una id de un alumno existente, intentalo de nuevo");
					System.out.println("Introduce la id del alumno que deseas eliminar");
					volverApedir = true;
				}
			}
			
			encontrado = new Select().buscarId(id);
			
			if (!encontrado) {
				System.out.println("Id de alumno no encontrado en la bbdd.");
				
			}else {
				// asignar datos introducidos a los interrogantes
				pst.setInt(1, id);
				// pulsamos el rayo del workbench para ejecutar la sentencia
				pst.executeUpdate();
				System.out.println("Alumno eliminado correctamente");
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

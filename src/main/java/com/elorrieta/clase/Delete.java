package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {

	public void borrarAlumno(Scanner sc) {

		// creracion de las variables que necesitamos para recoger los datos
		int id = 0;
		

		// declarar la sentencia que queremos
		String sql = "DELETE FROM alumno WHERE id_alumno = ?;";

		// conectar con la bbdd
		try (Connection con = Conexion.getConnection(); PreparedStatement pst = con.prepareStatement(sql);

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

			String sql2 = "SELECT id_alumno, nombre, email FROM clase.alumno WHERE id_alumno = ?;";
			PreparedStatement pst2 = con.prepareStatement(sql2);

			pst2.setInt(1, id);
			ResultSet rs2 = pst2.executeQuery();

			if (rs2.next()) {
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("Alumno eliminado correctamente\n");
			} else {
				System.out.println("La id seleccionada no se encuentra en la BBDD\n");
			}

		} catch (SQLException sqle) {
			System.out.println("Id de alumno no encontrado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

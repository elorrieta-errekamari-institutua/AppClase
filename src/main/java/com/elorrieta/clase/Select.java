package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Select {

	/**
	 * Lista los alumnos de la bbdd clase
	 */
	public void listar() {

		int i = 0;
		String sql = "SELECT id_alumno, nombre, email FROM clase.alumno ORDER BY id_alumno ASC;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

		) {

			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(" ID        nombre                email");
			System.out.println("-------------------------------------------------------------------------------");
			while (rs.next()) {

				int id = rs.getInt("id_alumno");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				System.out.printf(" %-10s %-17s %5s \n", id, nombre, email);

				i++;
			} // while

			System.out.println("\n+---------------------- TOTAL " + i + " Alumnos -------------------+\n\n");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

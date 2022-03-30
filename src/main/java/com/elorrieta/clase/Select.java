package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	/**
	 * Busca el id del alumno en la BBDD
	 * @param id recibe un id de alumno que mete el usuario
	 * @return true si el id existe, false si no existe
	 */
	public boolean buscarId(int id) {

		int i = 0;
		boolean encontrado = false;
		String sql = "SELECT id_alumno, nombre, email FROM clase.alumno ORDER BY id_alumno ASC;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

		) {

			while (rs.next()) {

				if (id == rs.getInt("id_alumno")) {
					encontrado = true;
				} else {
					encontrado = false;
				}

			} // while

		} catch (SQLException sqle) {

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encontrado;
	}

	/**
	 * Busca email de alumno
	 * @param email recibe email que inserta usuario para buscar si existe en la BBDD
	 * @return true si el email existe, false si no existe
	 */
	public boolean buscarEmail(String email) {

		int i = 0;
		boolean encontrado = false;
		String sql = "SELECT id_alumno, nombre, email FROM clase.alumno ORDER BY id_alumno ASC;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

		) {

			while (rs.next()) {

				if (email.equals(rs.getString("email"))) {
					encontrado = true;
				} else {
					encontrado = false;
				}

			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}

		return encontrado;
	}

}

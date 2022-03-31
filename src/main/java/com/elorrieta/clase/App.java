package com.elorrieta.clase;

// commit1
//commit2

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * App para hacer un CRUD completo para la bbdd de clase.sql
 * 
 * @author infauraga
 *
 */
public class App {

	// variables globales para poder usar en todos los metodos de esta Clase
	private static final int OPCION_LISTAR = 1;
	private static final int OPCION_INSERTAR = 2;
	private static final int OPCION_MODIFICAR = 3;
	private static final int OPCION_ELIMINAR = 4;
	private static final int OPCION_SALIR = 0;

	private static final String SQL_INSERTALUMNO = "INSERT INTO alumno (nombre, email) VALUES (?,?)";

	private static int opcion = 0; // opcion seleccionada por el usuario

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Comenzamos");
		boolean flag = true;

		do {

			opcion = menu();

			switch (opcion) {
			case OPCION_LISTAR:
				listar();
				break;

			case OPCION_INSERTAR:
				insertar();
				break;

			case OPCION_SALIR:
				flag = false;
				break;

			default:
				break;
			}

		} while (flag);

		System.out.println("Terminamos");

	}// main

	/**
	 * Buscamos un Alumno por su identificador
	 * 
	 * @param id int identificador del alumno, que es la columna id en la tabla
	 *           clase
	 * @return si lo encuentra me retorna el "nombre + email", si no lo encuentra
	 *         null
	 */
	@SuppressWarnings("unused")
	private static String buscarAlumno(int id) {

		String resul = null;
		String sql = "SELECT id_alumno, nombre, email FROM alumno WHERE id_alumno = ?;";
		try (Connection con = Conexion.getConnection(); 
			 PreparedStatement pst = con.prepareStatement(sql);)
	   {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				resul = rs.getString("nombre") + " " + rs.getString("email");
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

		return resul;
	}

	/**
	 * Pide por pantalla los datos de un alumno y lo inserta en la bbdd
	 */
	private static void insertar() {

		try (Connection con = Conexion.getConnection(); PreparedStatement pst = con.prepareStatement(SQL_INSERTALUMNO);

		) {

			System.out.println("Introduce el nombre");
			String nombre = sc.nextLine();

			System.out.println("Introduce el gmail");
			String email = sc.nextLine();

			//TODO validar campos y capturar excepcion
			
			pst.setString(1, nombre);
			pst.setString(2, email);

			pst.executeUpdate();
			System.out.println("Alumno insertado");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// insertar

	/**
	 * Muestra todos los alumnos por pantalla
	 */
	private static void listar() {

		String sql = "SELECT id_alumno, nombre, email FROM clase.alumno ORDER BY id_alumno DESC;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();

		) {

			System.out.println("-------------------------------------------------------");
			System.out.println(" ID            nombre            email");
			System.out.println("-------------------------------------------------------");
			while (rs.next()) {

				int id = rs.getInt("id_alumno");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				System.out.printf(" %-4s %-25s %s \n", id, nombre, email);

			} // while

			System.out.println("---------------------- TOTAL X Alumnos -------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// listar

	/**
	 * Pinta por pantalla el menu de la App y pide al usuario que seleccione una
	 * opcion
	 * 
	 * @return int opcion seleccionada por el usuario;
	 */
	private static int menu() {
		int op = 0;
		boolean error = false;

		System.out.println("----------------------------------------------------");
		System.out.println("-----   APP GESTION CLASE        -------------------");
		System.out.println("----------------------------------------------------");
		System.out.println(" 1 - Listar Alumnos");
		System.out.println(" 2 - Insertar Nuevo Alumno");
		System.out.println(" 3 - sdasdasdasd");
		System.out.println(" 4 - asdasdasdasd ");
		System.out.println("----------------------------------------------------");
		System.out.println(" 0 - Salir");
		System.out.println("----------------------------------------------------");

		do {
			try {
				op = Integer.parseInt(sc.nextLine().trim());
				if (opcion < 0 && opcion > 4) {
					error = true;
					System.out.println("Debe introducir un numero del 0 al 4");
				}
			} catch (Exception e) {
				error = true;
				System.out.println("Error en la introduccion de opcion, vuelve a introducir la opcion");
				e.printStackTrace();
			}
		} while (error);
		return op;
	}

}// App

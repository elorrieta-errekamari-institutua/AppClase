package com.elorrieta.clase;

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
	private static final String SQL_DELETEALUMNO = "DELETE FROM alumno WHERE id_alumno = ?";
	private static final String SQL_UPDATEALUMNO = "UPDATE alumno set nombre = ? , email = ? where id_alumno = ? ";

	private static int opcion = 0; // opcion seleccionada por el usuario

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		login();

	}// main

	/**
	 * comprobando datos de la bbdd para logearte
	 */
	private static void login() {

		int id = 0;
		String nombre = null;
		boolean error = false;

		do {
			try {
				System.out.println("Ingresa el ID");
				id = Integer.parseInt(sc.nextLine().trim());
				System.out.println("Ingresa el nombre");
				nombre = sc.nextLine();
			} catch (Exception e) {
				System.out.println("Error en la introduccion de datos");
			}
			if (getUnAlumno(id, nombre)) {
				System.out.println("BIENVENIDO");
				switchMenu();
				
			} else {
				System.out.println("Usuario o ID incorrecto, introduzca los datos de nuevo...");
				error=true;
			}
		} while (error);

	}

	private static boolean getUnAlumno(int idAl, String nombreAl) {
		boolean encontrado = false;
		String sql = "SELECT id_alumno, nombre FROM clase.alumno;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				if (rs.getInt(1) == idAl && rs.getString(2).equalsIgnoreCase(nombreAl)) {
					encontrado = true;
				}
			}
			// TODO falta repetir mientras no sea correcto
		} catch (Exception e) {
			e.printStackTrace();
		}

		return encontrado;
	}

	public static void switchMenu() {

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

			case OPCION_MODIFICAR:
				modificarAlumno();
				break;

			case OPCION_ELIMINAR:
				deleteAlumno();
				break;
			default:
				break;
			}

		} while (flag);

		System.out.println("Terminamos");
	}

	private static void deleteAlumno() {
		System.out.println("Escribe el ID del alumno que quieres eliminar: ");
		int id = Integer.parseInt(sc.nextLine().trim());

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_DELETEALUMNO);) {
			pst.setInt(1, id);
			int lineasEliminadas = pst.executeUpdate();
			if (lineasEliminadas > 0)
				System.out.println(" El alumno se a eliminado correctamente ");
			else
				System.out.println("No se ha encontrado el alumno");

		} catch (Exception e) {
			System.out.println("No se ha encontrado el usuario que desea eliminar");
		}
	}

	/**
	 * Modifica un alumno por id
	 * 
	 */
	private static void modificarAlumno() {
		System.out.println("Escribe el ID del alumno que quieres Modificar: ");
		int id = Integer.parseInt(sc.nextLine().trim());
		System.out.println(" NOMBRE ");
		String nombre = sc.nextLine();
		System.out.println(" EMAIL ");
		String email = sc.nextLine();
		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_UPDATEALUMNO);) {
			// UPDATE CLIENTE
			pst.setString(1, nombre);
			pst.setString(2, email);
			pst.setInt(3, id);
			int lineasEliminadas = pst.executeUpdate();
			if (lineasEliminadas > 0)
				System.out.println(" El alumno se a eliminado correctamente ");
			else
				System.out.println("No se ha encontrado el alumno");

		} catch (Exception e) {
			System.out.println("No se ha encontrado el usuario que desea eliminar");
		}
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

			// TODO validar campos y capturar excepcion de email capturado

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

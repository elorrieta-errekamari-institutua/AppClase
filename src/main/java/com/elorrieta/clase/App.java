package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
		//login();
		switchMenu();

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

			} else {
				System.out.println("Usuario o ID incorrecto, introduzca los datos de nuevo...");
				error = true;
			}
		} while (error);
	}

	private static boolean getUnAlumno(int idAl, String nombreAl) {
		boolean encontrado = false;
		String sql = "SELECT id_alumno, nombre FROM alumno ;";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				if (rs.getInt(1) == idAl && rs.getString(2).equalsIgnoreCase(nombreAl)) {
					encontrado = true;
				}
			}
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

		String alumno = buscarAlumno(id);
		System.out.println("Desea eliminar al alumno " + alumno + " ?");
		System.out.println("Pulsa S (si) / N (no)");
		String confirmar = sc.nextLine();

		if ("s".equalsIgnoreCase(confirmar)) {
			try (Connection con = Conexion.getConnection();
					PreparedStatement pst = con.prepareStatement(SQL_DELETEALUMNO);) {

				pst.setInt(1, id);
				int lineasEliminadas = pst.executeUpdate();
				if (lineasEliminadas == 1) {
					System.out.println(" El alumno se a eliminado correctamente ");
				} else {
					System.out.println("No se ha encontrado el alumno");
				}

			} catch (Exception e) {
				System.out.println("No se ha encontrado el usuario que desea eliminar");
			}

		} else {
			System.out.println("Volviendo al menu principal.....");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");

		}
	}

	/**
	 * Modifica un alumno por id
	 * 
	 */
	private static void modificarAlumno() {

		System.out.println("Escribe el ID del alumno que quieres Modificar: ");
		int id = Integer.parseInt(sc.nextLine().trim());

		String alumno = buscarAlumno(id);
		System.out.println("Desea modificar al alumno " + alumno + " ?");
		System.out.println("Pulsa S (si) / N (no)");
		String confirmar = sc.nextLine();

		if ("s".equalsIgnoreCase(confirmar)) {
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
					System.out.println(" El alumno se a modificado correctamente ");
				else
					System.out.println("No se ha encontrado el alumno");

			} catch (Exception e) {
				System.out.println("No se ha encontrado el usuario que desea eliminar");
			}
		} else {

			System.out.println("Volviendo al menu principal.....");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
		}
	}

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
		try (Connection con = Conexion.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
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
		boolean duplicate = false;

		do {
			try (Connection con = Conexion.getConnection();
					PreparedStatement pst = con.prepareStatement(SQL_INSERTALUMNO);) {

				System.out.println("Introduce el nombre");
				String nombre = App.isString();

				System.out.println("Introduce el gmail");
				String email = App.isGmail();

				pst.setString(1, nombre);
				pst.setString(2, email);

				pst.executeUpdate();
				System.out.println("Alumno insertado");
				duplicate = false;
			} catch (MySQLIntegrityConstraintViolationException e) {
				System.out.println("Email dupiclate ");
				duplicate = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (duplicate);
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

	public static String isGmail() {
		Scanner sc = new Scanner(System.in);
		Pattern pat = Pattern.compile("^([\\w]*[\\w\\.]*(?!\\.)@gmail.com)");
		String dni = sc.nextLine();
		Matcher mat = pat.matcher(dni);
		while (!mat.matches()) {
			System.out.println("Has introducido un gmail invalido, por favor introduzca uno válido.");
			System.out.print("Introduce un gmail válido: ");
			dni = sc.nextLine();
			mat = pat.matcher(dni);
		}
		return dni;

	}

	public static String isString() {
		Scanner sc = new Scanner(System.in);
		Pattern pat = Pattern.compile("[a-zA-Z ]{2,254}");
		String dni = sc.nextLine();
		Matcher mat = pat.matcher(dni);
		while (!mat.matches()) {
			System.out.println("Has introducido un nombre invalido, por favor introduzca uno válido.");
			System.out.print("Introduce un nombre válido: ");
			dni = sc.nextLine();
			mat = pat.matcher(dni);
		}
		return dni;

	}

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
		System.out.println("-----   APP GESTION CLASE   -------------------");
		System.out.println("----------------------------------------------------");
		System.out.println(" 1 - Listar Alumnos");
		System.out.println(" 2 - Insertar Nuevo Alumno");
		System.out.println(" 3 - Modificar");
		System.out.println(" 4 - Eliminar ");
		System.out.println("----------------------------------------------------");
		System.out.println(" 0 - Salir");
		System.out.println("----------------------------------------------------");

		do {
			try {
				op = App.isNumberMenu();
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
	}// App

public static int isNumberMenu() {
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Introduce la opcion deseada");
	int opcion = 0;
	try {
		opcion = sc.nextInt();
		if (!(1 <= opcion && opcion <= 4)) {
			System.out.println("El numero introducido no estaba entre las opciones posibles ( |1||2||3||4| )");
			opcion = isNumberMenu();
		}
	} catch (InputMismatchException e) {
		System.out.println();
		System.out.println("No has introducido un numero");
		System.out.println();
		opcion = isNumberMenu();
	}
	return opcion;
	}
}

package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

		// llamamos al metodo login para validar usuario y contraseña
	Login.control(sc);

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
	 * Pide por pantalla los datos de un alumno y lo inserta en la bbdd, Cuando el
	 * email esta repetido entra en un bucle y pide
	 */

	private static void insertar() {

		boolean validarEmail = true;
		String respuesta;
		// crea un bucle con un booleano dentro
		do {
			try (Connection con = Conexion.getConnection();
					PreparedStatement pst = con.prepareStatement(SQL_INSERTALUMNO);

			) {
				System.out.println("");
				
				System.out.println("Introduce el nombre");
				String nombre = sc.nextLine();
//Introduce un email que sea valido
				System.out.println("Introduce el email");
				String email = sc.nextLine();
				Pattern pattern = Pattern
		                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
				
				Matcher mather = pattern.matcher(email);
				if(mather.find() == true) {
					
					validarEmail = true;
					pst.setString(1, nombre);
					pst.setString(2, email);
					pst.executeUpdate();

					System.out.println("Alumno insertado");
				
				}else {
					validarEmail = false;
					System.out.println("EMAIL INTRODUCIDO NO VAALIDO");
				}
				
			} catch (Exception e) {
				// si el booleano es false sigue en el bucle y vuelve a pedir datos por pantalla
				validarEmail = false;

				System.out.println("ERROR, EMAIL YA REGISTRADO");

			}
		
			

		} while (validarEmail == false);
	} // metodo

// insertar

	/**
	 * Muestra todos los alumnos por pantalla y tambien el total de alumnos que hay
	 * en la base de datos
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

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Muestra el total de alumnos insertados
		String sql1 = "SELECT\r\n" + " count(nombre) total_alumno\r\n"
				+ "FROM clase.alumno ORDER BY id_alumno DESC;\r\n" + "";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql1);

				ResultSet rs = pst.executeQuery();

		) {

			System.out.println("");
			while (rs.next()) {

				String total_alumno = rs.getString("total_alumno");

				System.out.printf("TOTAL DE ALUMNOS : " + total_alumno);

				System.out.println("");

			} // while

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
			
			}
		} while (error);
		return op;
	}

}// App

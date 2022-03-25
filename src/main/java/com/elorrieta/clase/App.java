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
	
	public final String SQL_INSERTALUMNO = "INSERT INTO alumno VALUES (?,?,?)";

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
	
	
	public void insertarUsuario() throws SQLException {

//		try ( Connection con = Conexion.getConnection()) {
//
//			PreparedStatement psc = con.prepareStatement(SQL_INSERTALUMNO);
//			psc.setString(1, ));
//			psc.setString(2, );
//			psc.setString(3, );
//			psc.executeUpdate();
//
//		}
	}
	
	/**
	 * Pide por pantalla los datos de un alumno y lo inserta en la bbdd
	 */
	private static void insertar() {
//		System.out.println("TODO Insertado");
//
//		Connection con = Conexion.getConnection();
//		
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("Introduce el id");
//		String nombre = sc.next();
//		sc.getConnection();
//		System.out.println("Introduce el nombre");
//		String DNI = sc.next();
//		
//		System.out.println("Introduce el gmail");
//		String matricula = sc.next();
//		

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
<<<<<<< HEAD
			// TODO pintar el toal de alumnos

			System.out.println("Este es el segundo cambio de Iñaki");

		} catch (Exception e) {
=======
			//TODO pintar el toal de alumnos
			
			
						
		}catch (Exception e) {
>>>>>>> branch 'master' of https://github.com/elorrieta-errekamari-institutua/AppClase.git
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
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/elorrieta-errekamari-institutua/AppClase.git
	}// menu
	
	

}// App

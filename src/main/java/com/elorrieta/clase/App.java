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
	private static final String SQL_ELIMINARALUMNO = "delete from  clase.alumno where id_alumno = ? ;";
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

			case OPCION_ELIMINAR:
				eliminar();
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
	 * eliminar un alumno: pregunta a quien se quiere eliminar por id y si existe lo
	 * elimina, si no da error y vuelve a preguntar. Preguntar hasta que diga no
	 */
	private static void eliminar() {
		String muestra = "SELECT id_alumno,nombre,email,pass\r\n" + "FROM alumno\r\n" + "WHERE id_alumno = ?;";
		String respuesta;
		String comprobacion = null;
		boolean flateliminar = true;
		boolean flag2 = true;

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(SQL_ELIMINARALUMNO);
				PreparedStatement pst2 = con.prepareStatement(muestra); // hace un select para mostrar a quien borras
		) {
			do {
				//TODO HAY UN BUG rarisimo donde a la 2º vuelta si le dice que no quieres seguir, sigue, pero solo en una condicion especifica
				//TODO ¿como hacer si el usuario mete un espacio vacio?
				//TODO evitar que pongan algo que no sea numero
				System.out.println("Introduce el ID del alumno a eliminar ");
				int idalumno = Integer.parseInt(sc.nextLine());

				pst.setInt(1, idalumno);
				pst2.setInt(1, idalumno);
				ResultSet rs = pst2.executeQuery();

				if (rs.next()) { 

					System.out.println("-------------------------------------------------------");
					System.out.println(" ID            nombre            email");
					System.out.println("-------------------------------------------------------");
					

						int id = rs.getInt("id_alumno");
						String nombre = rs.getString("nombre");
						String email = rs.getString("email");
						System.out.printf(" %-4s %-25s %s \n", id, nombre, email);
					

				} else {
					System.out.println("no existe el alumno introducido\n");
					flag2 = false;
				} // else
				
				
				while (flag2) {
					System.out.println("¿quiere eliminar a este alumno?: si /no");
					comprobacion = sc.nextLine();

					if ("si".equalsIgnoreCase(comprobacion) || "no".equalsIgnoreCase(comprobacion)) {
						flag2 = false;
					} else {
						System.out.println("Por favor introduce SI o NO");
					}

				}
				
				
				if ("si".equalsIgnoreCase(comprobacion)) {
					int filasEliminadas = pst.executeUpdate();
					System.out.printf("entro la orden\n %s alumnos ha sido eliminados \n\n", filasEliminadas);

				} else if ("no".equalsIgnoreCase(comprobacion)) {
					flateliminar = false;
				}
			

				// preguntar si quieree seguir
				boolean flag = true;

				do {
					System.out.println("¿quiere seguir eliminando?: si /no");
					respuesta = sc.nextLine();

					if ("si".equalsIgnoreCase(respuesta) || "no".equalsIgnoreCase(respuesta)) {
						flag = false;
					} else {
						System.out.println("Por favor introduce SI o NO");
					}

				} while (flag);

				if ("si".equalsIgnoreCase(respuesta)) {
					eliminar();
				} else if ("no".equalsIgnoreCase(respuesta)) {
					flateliminar = false;
				}

				// TODO EXCPCINES

			} while (flateliminar);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
			e.printStackTrace();
		}

	}//fianl de eliminar

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

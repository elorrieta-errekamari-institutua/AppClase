package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
				new Select().listar();
				break;

			case OPCION_INSERTAR:
				insertar();
				break;
				
			case OPCION_MODIFICAR:
				new Update().modificar();
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
	 * Pinta por pantalla el menu de la App y pide al usuario que seleccione una
	 * opcion
	 * 
	 * @return int opcion seleccionada por el usuario;
	 */
	private int menu() {
		int op = 0;
		boolean error = false;

		System.out.println("----------------------------------------------------");
		System.out.println("-----   APP GESTION CLASE        -------------------");
		System.out.println("----------------------------------------------------");
		System.out.println(" 1 - Listar Alumnos");
		System.out.println(" 2 - Insertar Nuevo Alumno");
		System.out.println(" 3 - Modificar Alumno Existente");
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

	
	
	private void leerOpcionMenu(int opcion) {
	
		switch (opcion) {
		case 0: System.out.println("¡¡¡¡Que tennga un buen dia!!!!");
			System.exit(opcion);
			break;
		case 1:
			new Select().listar();
			break;

		case 2:
			insertar();
			break;

		case 3:System.out.println("Sin terminar!!!!!!!!!!!");
			//new Update().modificar();
			break;
			
		case 4: System.out.println("Sin terminar!!!!!!!!!!!");
			//new Delete().borrarAlumno();
			break;
		default: System.out.println("Opcion incorrecta, vuelva a seleccionar numero");
			opcion = 99;
		}
	}	
	
	public void empezarPrograma() {
		int opcion=0;
		
		do {
			opcion=menu();
			leerOpcionMenu(opcion);
		}while(opcion !=0);
	}


		
		
		

	
	
	



}// App

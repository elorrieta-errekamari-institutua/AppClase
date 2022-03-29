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
	private static int menu() {
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

		try {
			op = Integer.parseInt(sc.nextLine().trim());
		}catch(Exception e) {
			e.getMessage();
			op=99;
		}
		
		return op;
	}

	
	
	private static void leerOpcionMenu(int opcion) {
	
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
	
	public static void main(String[] args) {
		int opcion=0;
		
		do {
			opcion=menu();
			leerOpcionMenu(opcion);
		}while(opcion !=0);
	}


		
		
		

	
	
	



}// App

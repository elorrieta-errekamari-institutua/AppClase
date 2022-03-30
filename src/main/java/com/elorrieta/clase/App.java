package com.elorrieta.clase;

import java.util.Scanner;

/**
 * App para hacer un CRUD completo para la bbdd de clase.sql
 * 
 * @author infauraga, IbonJG, Javierbdp
 *
 */
public class App {

	public static Scanner sc = new Scanner(System.in);

	/**
	 * Login de usuario,para poder entrar introducir nombre de usuario "admin" y
	 * password "admin"
	 */
	private static void logueo() {

		String nombre = "";
		String pass = "";
		boolean entrar = false;

		do {
			System.out.println("Introduce el nombre del usuario");
			nombre = sc.nextLine();
			System.out.println("Introduce el password");
			pass = sc.nextLine();

			if (nombre.equalsIgnoreCase("admin") && pass.equals("admin")) {
				System.out.println("¡¡¡Bienvenido!!!\n");
				entrar = true;
			} else {
				System.out.println("Error con el nombre de usuario o password, por favor vuelva a intentarlo");
				entrar = false;
			}
		} while (!entrar);
	}

	/**
	 * Pinta por pantalla el menu de la App y pide al usuario que seleccione una
	 * opcion
	 * 
	 * @return int opcion seleccionada por el usuario;
	 */
	private static int menu() {
		int op = 0;

		System.out.println("----------------------------------------------------");
		System.out.println("-----   APP GESTION CLASE        -------------------");
		System.out.println("----------------------------------------------------");
		System.out.println(" 1 - Listar Alumnos");
		System.out.println(" 2 - Insertar Nuevo Alumno");
		System.out.println(" 3 - Modificar Alumno Existente");
		System.out.println(" 4 - Borrar Alumno ");
		System.out.println("----------------------------------------------------");
		System.out.println(" 0 - Salir");
		System.out.println("----------------------------------------------------");

		try {
			op = Integer.parseInt(sc.nextLine().trim());
		} catch (Exception e) {
			e.getMessage();
			op = 99;
		}

		return op;
	}

	private static void leerOpcionMenu(int opcion) {

		switch (opcion) {
		case 0:
			System.out.println("¡¡¡¡Que tennga un buen dia!!!!");
			sc.close();
			break;
		case 1:
			new Select().listar();
			break;

		case 2:
			new Insert().insertar(sc);
			break;

		case 3:
			new Update().modificar(sc);
			break;

		case 4:
			new Delete().borrarAlumno(sc);
			break;
		default:
			System.out.println("Opcion incorrecta, vuelva a seleccionar numero");
			opcion = 99;
		}
	}

	/**
	 * Main del programa, en este metodo se ejecuta todo el rato el programa hasta
	 * que el usuario introduzca 0
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion = 0;

		logueo();

		do {
			opcion = menu();
			leerOpcionMenu(opcion);
		} while (opcion != 0);
	}

}// App

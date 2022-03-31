package com.elorrieta.clase;

import java.util.Scanner;

/**
 * Un login de usuario que permite entrar al programa utilizando las claves
 * 
 * @author bmcaldarella
 *
 */
public class Login {

	private static final String USER_NAME = "admin";
	private static final String PASS = "admin";

	public static void control(Scanner sc) {

		// si el validador es true permite al while efectuar el bucle
		boolean validador = true;

		do {
			System.out.println("Ingrese un usuario: ");
			String user = sc.nextLine();

			System.out.println("Ingrese una contraseña: ");
			String pass = sc.nextLine();
			// si usuario y contraseña son igual a las variables final el validador es false
			// y sale del bucle
			if (USER_NAME.equals(user) && PASS.equals(pass)) {
				validador = false;
				
				// si el usuario y contraseña son incorrectas, el validador es true y sigue en
				// el bucle
			} else {
				validador = true;
				System.out.println("");
				System.out.println("INCORRECTO INGRESE UN USUARIO Y CONTRASEÑA VALIDOS");
			}
			// El bucle seguira mientras que validador sea true
		} while (validador);

	}
}

package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Ejemplo para trabajar con la clase String
 * @author infauraga
 *
 */
public class TrabajandoConString {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		//TODO pedir hasta que la longitud sea correcta
		System.out.println("Dime tu nombre(min 2 letras, maximo 45):");
		String nombre = sc.nextLine();
		
		if ( nombre.length() < 2 || nombre.length() > 45 ) {
			System.out.println("Longitud incorrecta");
		}
		
		
		//compara un string, hay que usar siempre equals o equalsIgnoreCase, NO USAR ==
		
		System.out.println("¿ Quieres introducir mas nombres? (Si o No)");
		String seguir = sc.nextLine();
		
		// TODO comprobar que escriba Si o No
		if ( "Si".equalsIgnoreCase(seguir) ) {
			System.out.println("Quiero seguir");
		}else {
			System.out.println("Agur");
		}
		

	}

}

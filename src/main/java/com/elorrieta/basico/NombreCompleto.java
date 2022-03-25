package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Pedir por pantalla nombre, apellido1 y apellido 2, luego saludar a la persona con el nombre completo en una sola linea
 * @author infauraga
 *
 */
public class NombreCompleto {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime tu nombre:");
		String nombre = sc.nextLine();
		
		System.out.println("Dime primer apellido:");
		String apellido1 = sc.nextLine();
		
		System.out.println("Dime segundo apellido:");
		String apellido2 = sc.nextLine();
		
		
		System.out.println("Hola " + nombre + ", " + apellido1 + " " + apellido2);
		sc.close();
		
		
	} // main		
}// clase

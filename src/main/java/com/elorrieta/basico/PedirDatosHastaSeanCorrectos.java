package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Pedir los datos de un alumno y mostrar en pantalla ( nombre, email y password ) una linea por cada dato, ejemplo "nombre: ander"
 * @author infauraga
 *
 */
public class PedirDatosHastaSeanCorrectos {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime tu nombre:");
		String nombre = sc.nextLine();
		
		System.out.println("Dime tu email:");
		String email = sc.nextLine();
		
		System.out.println("Dime contraseña:");
		String password = sc.nextLine();
		
		
		System.out.println("Datos del Alumno");
		System.out.println("---------------------------------------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Correo: " + email);
		System.out.println("Contraseña: " + password);
		System.out.println("---------------------------------------");
		
		sc.close();
		
		
	} // main		
}// clase

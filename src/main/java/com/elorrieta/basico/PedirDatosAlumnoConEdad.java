package com.elorrieta.basico;

import java.util.Random;
import java.util.Scanner;

/**
 * Pedir los datos de un alumno y mostrar en pantalla ( nombre, email y password ) una linea por cada dato, ejemplo "nombre: ander"
 * @author infauraga
 *
 */
public class PedirDatosAlumnoConEdad {
	
	/**
	 * Genera un numero aleatori del 0 al 100, ambos incluidos
	 * @return
	 */
	public static int generaNumeroAleatorio() {
		Random r = new Random();
		int low = 0;
		int high = 101;
		int result = r.nextInt(high-low) + low;
		return result;
	}
	

	public static void main(String[] args) {
			
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime tu nombre:");
		String nombre = sc.nextLine();
		
		System.out.println("Dime tu email:");
		String email = sc.nextLine();
		
		System.out.println("Dime contraseña:");
		String password = sc.nextLine();
		
		System.out.println("Dime tus años:");
		int edad = Integer.parseInt(sc.nextLine());
		
		
		
		System.out.println("Datos del Alumno");
		System.out.println("---------------------------------------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Correo: " + email);
		System.out.println("Contraseña: " + password);
		System.out.println("Edad: " + edad);
		
		if ( edad < 18 ) {
			System.out.println("Eres menor de edad");
		}else if ( edad > 99 ) {
			System.out.println("Eres muy viejo");
		}else {
			System.out.println("Tienes el rango de edad adecuado");
		}
		
		System.out.println("---------------------------------------");
		
		sc.close();
		
		
	} // main		
}// clase

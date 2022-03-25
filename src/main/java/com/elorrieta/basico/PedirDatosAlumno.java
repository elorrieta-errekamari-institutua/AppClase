package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Pedir la edad por pantalla, la cual debe estar entre 18 y 99
 * Seguir pidiendo hasta que sea correcto el dato.
 * @author infauraga
 *
 */
public class PedirDatosAlumno {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean seguirPidiendo = true;
		
		do {
		
			System.out.println("Dime yu edad (debe ser entre 18 y 99):");
			int edad = Integer.parseInt(sc.nextLine());
			
			if ( edad >= 18 && edad <= 99) {
				seguirPidiendo = false;
			}else {
				System.out.println("Edad incorrecta");
			}
			
			
		}while(seguirPidiendo); // edad >= 18 && edad <= 99
		
		System.out.println("Fin del programa");
		
		sc.close();
		
		
	} // main		
}// clase

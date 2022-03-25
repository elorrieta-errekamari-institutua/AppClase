package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Ejemplo basico para leer un numero entero (int) por teclado
 * 
 * @author infauraga
 *
 */
public class LeerEnteroConExcepcion {

	public static void main(String[] args) {
		System.out.println("dime un numero:");

		Scanner sc = new Scanner(System.in);

		// declaramos una variable de tipo entero
		int numero = 0;

		try {
			numero = Integer.parseInt(sc.nextLine());
			System.out.println("El numero es " + numero);
			
		}catch (Exception e) {
			System.out.println("Lo sentimos pero no es un numero correcto, motivo " + e.getMessage());
			// e.printStackTrace(); // muestra la traza de la excepcion
			
		}finally { // se ejecuta siempre
			System.out.println("Termina programa");
			sc.close();
		}
		

		

	}

}

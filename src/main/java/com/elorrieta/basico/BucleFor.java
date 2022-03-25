package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Pedir una frase y un numero, luego repertir la frase tantas veces como el numero introducido (for)
 * @author infauraga
 *
 */
public class BucleFor {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime una frase");
		String frase = sc.nextLine();
		
		System.out.println("Dime un numero para repetir la frase");
		int numero = Integer.parseInt( sc.nextLine() );

		for (int i = 0; i < numero; i++) {
			
			System.out.println( (i+1) + " " + frase);
			
			
		}// for
		
		
		sc.close();
	}

}

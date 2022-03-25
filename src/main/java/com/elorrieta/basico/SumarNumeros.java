package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Pedir dos numeros por pantalla y sumarlos
 * @author infauraga
 *
 */
public class SumarNumeros {


	
	public static void main(String[] args) {
		

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Dime el 1º numero para sumar");
		int n1 = Integer.parseInt( sc.nextLine() );
		
		System.out.println("Dime el 2º numero para sumar");
		int n2 = Integer.parseInt( sc.nextLine() );
		
		int suma = n1 + n2;
		System.out.println( n1 + " + " + n2 + " = " + suma);
		
		sc.close();
		
	}
	
}

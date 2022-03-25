package com.elorrieta.basico;

import java.util.Scanner;

/**
 * Solicta por consola al usuario su nombre y le saludar
 * @author Ander Uraga
 *
 */
public class HolaMundoScanner {
	
	public static void main(String[] args) {
		
		System.out.println("dime tu nombre:");
		
		Scanner sc = new Scanner(System.in);
		
		// decalramos una variable de tipo texto, incializada a cadena vacia
		String nombre = "";
		
		// leemos por pantalla y lo guaradmos en la variable
		nombre = sc.nextLine();
		
		System.out.println("Hola " + nombre);		
		
		System.out.println("Termina programa");
		sc.close();
		
	}// main

}// clase

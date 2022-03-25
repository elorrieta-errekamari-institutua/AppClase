package com.elorrieta.basico;

import java.util.Random;
import java.util.Scanner;

/**
 * Juego Adivina el Número: genera un numero del 0 al 100 de forma aleatoria, el
 * usuario tendra 5 intentos para adivinarlo. Como pista solo de puede decir si
 * es mayor o menor al numero que ha introducido el usuario. Si acierta darle la
 * enorabuena.
 * 
 * @author infauraga
 *
 */
public class JuegoAdivinaNumero {

	public static int generaNumeroAleatorio() {
		Random r = new Random();
		int low = 0;
		int high = 100;
		int result = r.nextInt(high - low) + low;
		return result;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int vidas = 5;
		int numAdivinar = generaNumeroAleatorio();
		System.out.println("**** Numero adivinar " + numAdivinar + "******");

		for (vidas = 5; vidas > 0; vidas--) {

			System.out.println("Dime un numero entre 0 y 100");
			int numero = Integer.parseInt(sc.nextLine());
			
			if (numero == numAdivinar) {
				System.out.println("Enhorabuena has ganado");
				break; // sale del bucle
				
			} else {
				
				if ( numero < numAdivinar ) {
					System.out.println("pista: El numero es mayor");
				}else {
					System.out.println("pista: El numero es menor");
				}
				System.out.println("Sigue intentandolo, te quedan " + (vidas - 1) + " vidas");
				
			}// 1ºelse

		} // for

		if ( vidas == 0) {
			System.out.println("Lo siento PERDISTE!!!!!!!!!!!!!");
		}

	}

}

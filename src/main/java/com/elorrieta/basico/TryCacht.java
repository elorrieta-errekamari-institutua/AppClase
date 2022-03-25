package com.elorrieta.basico;

import java.util.Scanner;

public class TryCacht {

	public static void main(String[] args) {
		
		int num1 = 0;
		int num2 = 0;
		float num3 = 0;
		float result = 0;
		boolean lel = true;
		boolean lol = true;
		boolean lul = true;

		Scanner sc = new Scanner(System.in);
		System.out.println("vamos a dividor ");
		
		while (lul) {
			try {
				System.out.println("mete un nunmero con decimal, cuidad usar '.' no ','");
				num3 = Float.parseFloat(sc.nextLine());
				lul = false;

			} catch (Exception e) {				
				System.out.println("bobo");
			} // catch
		} // while
		
		
		while (lel) {
			try {
				System.out.println("mete el numerador");
				num1 = Integer.parseInt(sc.nextLine());
				lel = false;

			} catch (Exception e) {				
				System.out.println("bobo");
			} // catch
		} // while
		

		while (lol) {
			try {
				System.out.println("mete el denominador");
				num2 = Integer.parseInt(sc.nextLine());
				lol = false;

			} catch (Exception e) {				
				System.out.println("bobo, intentalo de nuevo");
			} // catch
		} // whil

		try {

			result = num1 + num2 + num3;
			System.out.println(result);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e.getMessage());
		}

		System.out.println("lol bien hecho, ");
		sc.close();
	}// main

}

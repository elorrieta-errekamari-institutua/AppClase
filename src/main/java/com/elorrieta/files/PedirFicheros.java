package com.elorrieta.files;

import java.io.File;
import java.util.Scanner;

public class PedirFicheros {
	
	static public final String PATH_FICHEROS = "ficheros//";
	static public final String EXTENSION_FICHEROS = ".txt";
	
	public static File buscarFichero() throws Exception {
	
		File file = null;
	
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime el nombre del fichero:");
		String nombre  = sc.nextLine();
		sc.close();
		
		file = new File( PATH_FICHEROS + nombre + EXTENSION_FICHEROS);
		
		if ( !file.exists() ) {
			throw new Exception("NO EXISTE " + nombre);
		}
		
		return file;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		File f1 = buscarFichero();
		File f2 = buscarFichero();
		
		System.out.println(f1);
		System.out.println(f2);
		
	}

}

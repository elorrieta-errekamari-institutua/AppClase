package com.elorrieta.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EscribirPedidoEnFicheroCSV {

	public static void main(String[] args) throws IOException {
		
		Pedido p1 = new Pedido("palomitas", 24);
		Pedido p2 = new Pedido("Cafe", 12);
		Pedido p3 = new Pedido("torilla pataas", 1);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Dime el nombre del fichero:");
		String nombre  = sc.nextLine();
		sc.close();
		
		
		File f = new File(nombre + ".txt");
		if ( !f.exists() ) {
			f.createNewFile();
		}
		
		// escribir los pedidos en fichero, mirar documentacion para el TRUE que hace append		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
		bw.write( p1.getId() + ";" + p1.getNombre() + ";" + p1.getCantidad() + "\n");
		bw.write( p2.getId() + ";" + p2.getNombre() + ";" + p2.getCantidad() + "\n");
		bw.write( p3.getId() + ";" + p3.getNombre() + ";" + p3.getCantidad() + "\n");		
		bw.close();
		
		

	}

}

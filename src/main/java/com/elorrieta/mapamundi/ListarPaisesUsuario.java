package com.elorrieta.mapamundi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Lista los paises de un continente que quiera el usuario
 * @author infauraga
 *
 */
public class ListarPaisesUsuario {

	public static void main(String[] args) {

		try {
		
			// Pedir el contiente al Usuario
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Dime un contiente, para listar sus paises:");
			String contienteUsuario = sc.nextLine();
			//String contienteUsuario = "Europa";
			
						
			//Ejecutar la consulta contra la bbdd			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mapamundi?useSSL=false", "root", "root");
			String sql ="SELECT p.nombre `pais` "
							+ "FROM continente c, pais p "
							+ "WHERE c.continente_id = p.continente_id "
							+ "      AND LOWER(c.nombre) = LOWER( ? ) "
							+ "ORDER BY p.nombre ASC; ";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			// cambiar el ? por el contiente seleccionado por el usuario
			pst.setString(1, contienteUsuario);
			
			ResultSet rs = pst.executeQuery();
			
			
			int contador = 0;
			while( rs.next() ) {
				
				if ( contador == 0) {
					System.out.printf("El contiente %s tiene los paises: \n" , contienteUsuario);
					System.out.println("-------------------------------------------");
				}	
				
				String pais = rs.getString("pais");						
				System.out.println( pais );
				contador++;
				
			}// while
			
			if ( contador == 0) {
				System.out.printf("El contiente %s no tiene paises \n", contienteUsuario);
			}
			
		
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		
	}// main

}// clase

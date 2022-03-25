package com.elorrieta.mapamundi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListarContinentesPaises {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try {	
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/mapamundi", "root", "root");
			String sql = " SELECT c.nombre `continente`, p.nombre `pais` "
						+ " FROM pais p, continente c "
						+ " WHERE p.continente_id = c.continente_id "
						+ " ORDER BY c.nombre ASC, p.nombre ASC; ";
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			String cambioContiente = "";
			while( rs.next() ) {				
				
				String continente = rs.getString("continente");	
				String pais  = rs.getString("pais");
				
				if ( !cambioContiente.equals(continente) ){
					
					cambioContiente = continente;
					
					System.out.println("-------------------------");
					System.out.println(continente);
					System.out.println("-------------------------");
					
				}
				
				
				System.out.println("    " + pais);
				
			}// while
		
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");
		
	}// main

}// clase

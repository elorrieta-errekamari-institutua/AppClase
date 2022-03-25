package com.elorrieta.rendimiento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertMasiva {

	public static void main(String[] args) {

		System.out.println("Empieza el programa");
		
		try {
		
			
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rendimiento?useSSL=false", "root", "root");
			//String sql = "INSERT INTO `authors` (`first_name`, `last_name`, `email`, `birthdate`) VALUES ('dummy', 'dummy_last', 'dummy@dummy.com', '2022-03-11');";
			String sql = "INSERT INTO `authors` (`first_name`, `last_name`, `email`, `birthdate`) VALUES ( ?, ?, ?, ?);";
			PreparedStatement pst = conexion.prepareStatement(sql);
			
			for (int i = 0; i < 1000000 ; i++) {
				
				// cambiamos los ? de la SQL por los valores que nosotros queremos
				pst.setString(1, i + "dummy_first" );        // first_name
				pst.setString(2, i + "dummy_last" );         // last_name
				pst.setString(3, i + "dummy@dummy.com" );    // email
				pst.setDate(4, Date.valueOf("2022-03-11") ); // birthdate
				
				pst.executeUpdate(); // realiza la insert, es como cuando damos al rayo en workbench
				
				
				// cada 1000 registros insertado, escribimos por pantalla
				if ( i % 1000 == 0 ) {
					System.out.println( i + " registros insertados");
				}
				
				
			}
	
		}catch (Exception e) {
			
			System.out.println("Hemos tenido un problema");
			e.printStackTrace();
		}	
		

		System.out.println("Termina el programa");

	}

}

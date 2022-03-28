package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	
	
	/**
	 * Obtenemos la conexion a la bbdd de clase.sql
	 * la bbdd tiene datos de alumnos con un id, su nombre y su email
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clase?useSSL=false", "root", "root");
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
			e.printStackTrace();
		}
		
		return con;
	}

}

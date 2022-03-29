package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static String BBDD_URL = "jdbc:mysql://localhost:3306/clase?useSSL=false";
	private static String USER = "root";
	private static String PASS =  "root";
	
	/**
	 * Obtenemos la conexion a la bbdd de clase.sql
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(BBDD_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("ERROR de conexion");
			e.printStackTrace();
		}
		
		return con;
	}

}

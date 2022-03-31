package com.elorrieta.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {

	public void modificar(Scanner sc) {

		//declaracion de variables
		String nombre;
		String email;
		int id = 0;
		

		//sentencia sql que necesitamos
		String sql = "UPDATE alumno SET nombre = ?, email = ? WHERE id_alumno = ?;";

		//conectar con la bbdd
		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);

		) {

			System.out.println("Introduce la id del alumno que deseas modificar");
			//gestion de error si mete letras en vez de numeros
			boolean volverApedir = true;
			while (volverApedir) {
				try {
					id = Integer.parseInt(sc.nextLine());
					volverApedir = false;
				} catch (Exception e) {
					System.out.println("No has introducido un valor correcto, intentalo de nuevo");
					volverApedir=true;
				}
			
				//sentencia para preguntar si la id introducida existe en la bbdd
				String sql2 = "SELECT id_alumno, nombre, email FROM clase.alumno WHERE id_alumno = ?;";
				PreparedStatement pst2 = con.prepareStatement(sql2);
				// asignar datos introducidos a los interrogantes
				pst2.setInt(1, id);
				ResultSet rs2 = pst2.executeQuery();
				//si la id existe pedimos los datos
				if(rs2.next()){
					System.out.println("El nombre actual es : " + rs2.getString("nombre"));
		            System.out.println("Introduce un nuevo nombre");
					nombre = sc.nextLine();
	
					System.out.println("El email actual es : " + rs2.getString("email"));
					System.out.println("Introduce un email");
					email = sc.nextLine();
	
					// asignar datos introducidos a los interrogantes
					pst.setString(1, nombre);
					pst.setString(2, email);
					pst.setInt(3, id);
	
					// ejecutar la select
					pst.executeUpdate();
					System.out.println("Alumno Actualizado");
	
		        }
		        else{
		           System.out.println("La id introducida no se encuentra en la base de datos, el alumno no existe");
		           volverApedir=true;
		        }
			}
			
		} catch (SQLException sqle) {
			System.out.println("Error al modificar, debe introducir un email.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

package com.elorrieta.files;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Guardamos tres objetos serializados en un fichero "pedidos.dat"
 * @author infauraga
 *
 */
public class EscribirPedidoEnFicheroObjetos {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		Pedido p1 = new Pedido("palomitas", 24);
		Pedido p2 = new Pedido("Cafe", 12);
		Pedido p3 = new Pedido("torilla pataas", 1);
		
				
		File f = new File("pedidos.dat");
		if ( !f.exists() ) {
			f.createNewFile();
		}		
		
		// escribir los pedidos en fichero Pedido debe implementar la interfaz Serializable			
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f, true));
      
        oos.writeObject(p1);
        oos.writeObject(p2);
        oos.writeObject(p3);

        oos.close();
        
        System.out.println("Escrtito en fichero 3 objetos");
        
		
		// vamos a leer el fichero con objetos
        
       
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
        
        Pedido p = null;
        while ( true) {
        	
        	try {
        		p = (Pedido) ois.readObject();
        		System.out.println(p.toString());
        	}catch (EOFException e) {
				// Cuando finalice el fichero EndOfFileException salimos
        		break;
			}	
        }
        

        ois.close();
        
		

	}

}

package com.elorrieta.modelo;

import java.util.List;

public interface IPedidoDAO {
	
	
	List<Pedido> getAll();
	
	Pedido getById(int id);
	
	boolean delete(int id) throws Exception;
	
	boolean update(int id, Pedido p) throws Exception;
	
	Pedido insert(Pedido p) throws Exception;
	
	
	
	

}

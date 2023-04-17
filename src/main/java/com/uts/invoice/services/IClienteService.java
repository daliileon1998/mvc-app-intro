package com.uts.invoice.services;

import java.util.List;
import com.uts.invoice.models.entities.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Cliente cliente);

}

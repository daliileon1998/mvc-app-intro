package com.uts.invoice.services;

import java.util.List;
import com.uts.invoice.models.entities.Cliente;
import com.uts.invoice.models.entities.Factura;
import com.uts.invoice.models.entities.Producto;
import com.uts.invoice.models.entities.Region;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Cliente cliente);
	
	public List<Region> findAllRegiones();
	
	public Factura findFacturaById(Long id);
	
	public List<Factura> findFacturaAll();
	
	public Factura saveFactura(Factura factura);
	
	public void deleteFacturaById(Long id);
	
	public List<Producto> findProductoByNombre(String term);

}

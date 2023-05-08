package com.uts.invoice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uts.invoice.models.dao.IClienteDao;
import com.uts.invoice.models.dao.IFacturaDao;
import com.uts.invoice.models.dao.IProductoDao;
import com.uts.invoice.models.entities.Cliente;
import com.uts.invoice.models.entities.Factura;
import com.uts.invoice.models.entities.Producto;
import com.uts.invoice.models.entities.Region;


@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	
	@Autowired
	private IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		clienteDao.delete(cliente);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long id) {
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Factura> findFacturaAll() {
		return (List<Factura>) facturaDao.findAll();
	}

	@Override
	@Transactional
	public Factura saveFactura(Factura factura) {
		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFacturaById(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findProductoByNombre(String term) {
		return productoDao.findByNombreContainingIgnoreCase(term);
	}


}

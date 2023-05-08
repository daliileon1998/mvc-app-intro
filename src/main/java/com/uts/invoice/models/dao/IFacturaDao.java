package com.uts.invoice.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.uts.invoice.models.entities.Factura;

public interface IFacturaDao extends CrudRepository<Factura,Long> {


}

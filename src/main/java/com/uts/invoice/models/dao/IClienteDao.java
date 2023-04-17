package com.uts.invoice.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.uts.invoice.models.entities.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long> {

}

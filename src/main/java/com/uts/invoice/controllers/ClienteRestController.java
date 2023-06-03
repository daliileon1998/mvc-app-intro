 package com.uts.invoice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uts.invoice.models.entities.Cliente;
import com.uts.invoice.models.entities.Region;
import com.uts.invoice.services.IClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/uts")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}

	@GetMapping("/cliente/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result){
		
		Cliente clienteNew=null;
		
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors= result.getFieldErrors()
								 .stream()
								 .map(err->"El campo "+ err.getField() + " " + err.getDefaultMessage())
								 .collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = this.clienteService.save(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id){
		
		Cliente currentCliente=this.clienteService.findById(id);
		
		Cliente updateCliente=null;
		
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors= result.getFieldErrors()
					 .stream()
					 .map(err->"El campo "+ err.getField() + " " + err.getDefaultMessage())
					 .collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(currentCliente==null) {
			response.put("mensaje", "Error: no se puede editar, el cliente ID: ".concat(id.toString())
					.concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		try {
			currentCliente.setNombre(cliente.getNombre());
			currentCliente.setApellido(cliente.getApellido());
			currentCliente.setEmail(cliente.getEmail());
			updateCliente=this.clienteService.save(currentCliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("Cliente", updateCliente);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Map<String,Object> response= new HashMap<>();
		try {
			Cliente cliente=this.clienteService.findById(id);
			this.clienteService.delete(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	
}

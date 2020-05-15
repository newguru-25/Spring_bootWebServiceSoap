package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Cliente;

public interface ClienteService {
	
	public List<Cliente> getAllCliente();
	
	public Optional<Cliente> findByIdClien(Integer id);
	
	public Cliente updateById(Cliente cliente);
	
	public void eliminarClliente(Integer id);
	
	public Cliente addCliente(Cliente cliente);
	
}

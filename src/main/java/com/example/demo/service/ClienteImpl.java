package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;


@Service
public class ClienteImpl implements ClienteService {

	
	@Autowired
	ClienteRepository clienterepository;
	
	@Override
	public List<Cliente> getAllCliente() {		
		Iterable<Cliente> myIterator = clienterepository.findAll();
		
		List<Cliente> myList = Lists.newArrayList(myIterator);		

		return myList;
	}

	@Override
	public Optional<Cliente> findByIdClien(Integer id) {
Optional<Cliente> clien = clienterepository.findById(id);
	
		return clien;
	}

	@Override
	public Cliente updateById(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienterepository.save(cliente);
	}

	@Override
	public void eliminarClliente(Integer id) {
		// TODO Auto-generated method stub		
		clienterepository.deleteById(id);			
		
	}

	@Override
	public Cliente addCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienterepository.save(cliente);
	}

}

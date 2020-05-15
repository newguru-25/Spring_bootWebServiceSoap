package com.example.demo.endpoint;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.demo.wssoap.AddClienteRequest;
import com.demo.wssoap.AddClienteResponse;
import com.demo.wssoap.ClienteType;
import com.demo.wssoap.DeleteClienteRequest;
import com.demo.wssoap.DeleteClienteResponse;
import com.demo.wssoap.GetClienteByIdRequest;
import com.demo.wssoap.GetClienteByIdResponse;
import com.demo.wssoap.GetClienteRequest;
import com.demo.wssoap.GetClienteResponse;
import com.demo.wssoap.ServiceStatus;
import com.demo.wssoap.UpdateClienteRequest;
import com.demo.wssoap.UpdateClienteResponse;

import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;

@Endpoint
public class ClientesEndpoint {

	public static final String NAMESPACE_URI = "http://www.demo.com/wssoap";

	private ClienteService service;

	public ClientesEndpoint() {

	}

	@Autowired
	public ClientesEndpoint(ClienteService service) {
		this.service = service;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClienteRequest")
	@ResponsePayload
	public GetClienteResponse getAllMovies(@RequestPayload GetClienteRequest request) {
		GetClienteResponse response = new GetClienteResponse();
		ClienteType clientipo = new ClienteType();

		for (Cliente result : service.getAllCliente()) {
			BeanUtils.copyProperties(result, clientipo);
			response.getClienteType().add(clientipo);
		}

		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClienteByIdRequest")
	@ResponsePayload
	public GetClienteByIdResponse findByIdCliente(@RequestPayload GetClienteByIdRequest request) {
		GetClienteByIdResponse response = new GetClienteByIdResponse();
		ClienteType clientipo = new ClienteType();

		Optional<Cliente> result = service.findByIdClien(request.getIdCliente());
		BeanUtils.copyProperties(result.get(), clientipo);

		response.setClienteType(clientipo);

		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateClienteRequest")
	@ResponsePayload
	public UpdateClienteResponse updateById(@RequestPayload UpdateClienteRequest request) {
		UpdateClienteResponse response = new UpdateClienteResponse();

		Cliente cliente = new Cliente();
		ServiceStatus serviceStatus = new ServiceStatus();

		BeanUtils.copyProperties(request.getClienteType(), cliente);

		Cliente clie = service.updateById(cliente);

		if (clie != null) {
			serviceStatus.setMessage("EXITOSO");
			serviceStatus.setStatusCode("200");
		} else {
			serviceStatus.setMessage("FAIL");
			serviceStatus.setStatusCode("0");
		}

		response.setServiceStatus(serviceStatus);

		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteClienteRequest")
	@ResponsePayload
	public DeleteClienteResponse updateById(@RequestPayload DeleteClienteRequest request) {

		DeleteClienteResponse response = new DeleteClienteResponse();

		ServiceStatus serviceStatus = new ServiceStatus();		
			service.eliminarClliente(request.getIdCliente());
			serviceStatus.setMessage("EXITOSO");
			serviceStatus.setStatusCode("200");
			
		response.setServiceStatus(serviceStatus);

		return response;

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addClienteRequest")
	@ResponsePayload
	public AddClienteResponse addCliente(@RequestPayload AddClienteRequest request) {
		AddClienteResponse response = new AddClienteResponse();

		Cliente cliente = new Cliente();

		ServiceStatus serviceStatus = new ServiceStatus();

		BeanUtils.copyProperties(request.getClienteType(), cliente);

		Cliente clie = service.addCliente(cliente);

		if (clie != null) {
			serviceStatus.setMessage("EXITOSO");
			serviceStatus.setStatusCode("200");
		} else {
			serviceStatus.setMessage("FAIL");
			serviceStatus.setStatusCode("0");
		}

		response.setServiceStatus(serviceStatus);

		return response;

	}

}

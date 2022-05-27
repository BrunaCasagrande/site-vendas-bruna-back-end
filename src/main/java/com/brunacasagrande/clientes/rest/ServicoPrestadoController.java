package com.brunacasagrande.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.brunacasagrande.clientes.model.entity.Cliente;
import com.brunacasagrande.clientes.model.entity.ServicoPrestado;
import com.brunacasagrande.clientes.repository.ClienteRepository;
import com.brunacasagrande.clientes.repository.ServicoPrestadoRepository;
import com.brunacasagrande.clientes.rest.dto.ServicoPrestadoDTO;
import com.brunacasagrande.clientes.util.BigDecimalConverter;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteReository;
	private final ServicoPrestadoRepository servicoRepository;
	private final BigDecimalConverter bigDecimalConverter;
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente = 
				clienteReository
					.findById(idCliente)
					.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Cliente não existente"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return servicoRepository.save(servicoPrestado);
		
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "")String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			){
		return servicoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
}

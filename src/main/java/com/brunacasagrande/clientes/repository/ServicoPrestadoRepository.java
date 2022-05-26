package com.brunacasagrande.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunacasagrande.clientes.model.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer>{

	List<ServicoPrestado> findByNomeClienteAndMes(String nome, Integer mes);

}

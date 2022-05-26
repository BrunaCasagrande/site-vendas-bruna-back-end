package com.brunacasagrande.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunacasagrande.clientes.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}

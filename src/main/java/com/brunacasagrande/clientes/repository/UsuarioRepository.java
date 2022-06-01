package com.brunacasagrande.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.brunacasagrande.clientes.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}

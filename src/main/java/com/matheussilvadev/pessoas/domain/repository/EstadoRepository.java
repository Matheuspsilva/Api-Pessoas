package com.matheussilvadev.pessoas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheussilvadev.pessoas.model.Cidade;
import com.matheussilvadev.pessoas.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
	

}

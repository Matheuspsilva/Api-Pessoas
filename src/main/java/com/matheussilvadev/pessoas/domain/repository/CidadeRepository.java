package com.matheussilvadev.pessoas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheussilvadev.pessoas.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	

}

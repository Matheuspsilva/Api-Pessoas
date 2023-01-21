package com.matheussilvadev.pessoas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheussilvadev.pessoas.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}

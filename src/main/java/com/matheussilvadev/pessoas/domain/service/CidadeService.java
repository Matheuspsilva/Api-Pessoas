package com.matheussilvadev.pessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheussilvadev.pessoas.domain.exception.CidadeNaoEncontradaException;
import com.matheussilvadev.pessoas.domain.model.Cidade;
import com.matheussilvadev.pessoas.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

}

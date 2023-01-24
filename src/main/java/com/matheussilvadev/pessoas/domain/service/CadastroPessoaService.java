package com.matheussilvadev.pessoas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.matheussilvadev.pessoas.domain.exception.PessoaNaoEncontradaException;
import com.matheussilvadev.pessoas.domain.repository.PessoaRepository;
import com.matheussilvadev.pessoas.model.Cidade;
import com.matheussilvadev.pessoas.model.Pessoa;

@Service
public class CadastroPessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CidadeService cidadeService;

	public Pessoa salvar(Pessoa pessoa) {
		
		
		Long cidadeId = pessoa.getEndereco().getCidade().getId();
		
		if(cidadeId != null) {
			Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

			pessoa.getEndereco().setCidade(cidade);

		}
		
		return pessoaRepository.save(pessoa);
	}

	public void excluir(Long id) {
		
		try {
			pessoaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new PessoaNaoEncontradaException(id);
		}
		

	}

	public Pessoa buscarOuFalhar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId).orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
	}
}

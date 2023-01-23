package com.matheussilvadev.pessoas.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.matheussilvadev.pessoas.domain.repository.PessoaRepository;
import com.matheussilvadev.pessoas.domain.service.CadastroPessoaService;
import com.matheussilvadev.pessoas.model.Pessoa;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CadastroPessoaService cadastroPessoaService;

	@GetMapping("/")
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Pessoa buscar(@PathVariable Long id) {
		return cadastroPessoaService.buscarOuFalhar(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		System.out.println(pessoa.toString());
		return cadastroPessoaService.salvar(pessoa);
	}

	@PutMapping("/{id}")
	public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
		
		Pessoa pessoaAtual = cadastroPessoaService.buscarOuFalhar(id);

		BeanUtils.copyProperties(pessoa, pessoaAtual, "id");
		pessoa.setId(pessoaAtual.getId());

		return cadastroPessoaService.salvar(pessoa);

	}
	
	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long pessoaId) {
		cadastroPessoaService.excluir(pessoaId);
	}

}

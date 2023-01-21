package com.matheussilvadev.pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@RequestBody Pessoa pessoa) {
		System.out.println(pessoa.toString());
		return cadastroPessoaService.salvar(pessoa);
	}
}

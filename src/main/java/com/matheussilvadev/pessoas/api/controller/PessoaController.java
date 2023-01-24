package com.matheussilvadev.pessoas.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	CadastroPessoaService cadastroPessoaService;

	@ApiOperation("Lista as pessoas ")
	@GetMapping("/")
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@ApiOperation("Busca uma pessoa por id")
	@GetMapping("/{pessoaId}")
	public Pessoa buscar(
			@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId) {
		return cadastroPessoaService.buscarOuFalhar(pessoaId);
	}

	@ApiOperation("Cadastra uma pessoa")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova cidade") @RequestBody @Valid Pessoa pessoa) {
		System.out.println(pessoa.toString());
		return cadastroPessoaService.salvar(pessoa);
	}

	@ApiOperation("Atualiza uma pessoa por id")
	@PutMapping("/{pessoaId}")
	public Pessoa atualizar(
			@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId,
			@ApiParam(name = "corpo", value = "Representação de pessoa com os novos dados") @RequestBody @Valid Pessoa pessoa) {
		
		Pessoa pessoaAtual = cadastroPessoaService.buscarOuFalhar(pessoaId);

		BeanUtils.copyProperties(pessoa, pessoaAtual, "id");
		pessoa.setId(pessoaAtual.getId());

		return cadastroPessoaService.salvar(pessoa);

	}
	
	@ApiOperation("Remove uma pessoa por id")
	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId) {
		cadastroPessoaService.excluir(pessoaId);
	}

}

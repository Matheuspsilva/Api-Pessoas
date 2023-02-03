package com.matheussilvadev.pessoas.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.matheussilvadev.pessoas.api.assembler.PessoaAssembler;
import com.matheussilvadev.pessoas.api.assembler.PessoaInputDisassembler;
import com.matheussilvadev.pessoas.api.model.PessoaModel;
import com.matheussilvadev.pessoas.api.model.input.PessoaInput;
import com.matheussilvadev.pessoas.domain.exception.CidadeNaoEncontradaException;
import com.matheussilvadev.pessoas.domain.exception.NegocioException;
import com.matheussilvadev.pessoas.domain.model.Pessoa;
import com.matheussilvadev.pessoas.domain.repository.PessoaRepository;
import com.matheussilvadev.pessoas.domain.service.CadastroPessoaService;

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

	@Autowired
	PessoaAssembler pessoaAssembler;

	@Autowired
	PessoaInputDisassembler pessoaInputDisassembler;

	@ApiOperation("Lista as pessoas ")
	@GetMapping("/")
	public List<PessoaModel> listar() {
		return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
	}

	@ApiOperation("Busca uma pessoa por id")
	@GetMapping("/{pessoaId}")
	public PessoaModel buscar(@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId) {

		Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(pessoaId);

		return pessoaAssembler.toModel(pessoa);
	}

	@ApiOperation("Cadastra uma pessoa")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova pessoa") @RequestBody @Valid PessoaInput pessoaInput) {
		Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);

		pessoa = cadastroPessoaService.salvar(pessoa);

		return pessoaAssembler.toModel(pessoa);
	}

	@ApiOperation("Atualiza uma pessoa por id")
	@PutMapping("/{pessoaId}")
	public PessoaModel atualizar(@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId,
			@ApiParam(name = "corpo", value = "Representação de pessoa com os novos dados") @RequestBody @Valid PessoaInput pessoaInput) {

		Pessoa pessoaAtual = cadastroPessoaService.buscarOuFalhar(pessoaId);

		pessoaInputDisassembler.copyToDomainObject(pessoaInput, pessoaAtual);

		try {

			pessoaAtual = cadastroPessoaService.salvar(pessoaAtual);

		} catch (CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

		return pessoaAssembler.toModel(pessoaAtual);
	}

	@ApiOperation("Remove uma pessoa por id")
	@DeleteMapping("/{pessoaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@ApiParam(value = "ID de uma pessoa", example = "1") @PathVariable Long pessoaId) {
		cadastroPessoaService.excluir(pessoaId);
	}

}

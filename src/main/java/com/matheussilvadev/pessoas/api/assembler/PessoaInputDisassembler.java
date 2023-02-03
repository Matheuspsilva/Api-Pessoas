package com.matheussilvadev.pessoas.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matheussilvadev.pessoas.api.model.input.PessoaInput;
import com.matheussilvadev.pessoas.domain.model.Cidade;
import com.matheussilvadev.pessoas.domain.model.Pessoa;

@Component
public class PessoaInputDisassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Pessoa toDomainObject(PessoaInput pessoaInput) {
		return  modelMapper.map(pessoaInput, Pessoa.class);
	}
	
	public void copyToDomainObject(PessoaInput pessoaInput, Pessoa pessoa) {
		if(pessoa.getEndereco() != null) {
			pessoa.getEndereco().setCidade(new Cidade());
		}
		modelMapper.map(pessoaInput, pessoa);
	}

}

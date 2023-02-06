package com.matheussilvadev.pessoas.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel {
	
	private String nome;
	
	private OffsetDateTime dataNascimento;
	
	private EnderecoModel endereco;

}

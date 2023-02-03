package com.matheussilvadev.pessoas.api.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaModel {
	
	private String nome;
	
	private LocalDateTime dataNascimento;
	
	private EnderecoModel endereco;

}

package com.matheussilvadev.pessoas.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@Column(name = "endereco_cep")
	private String cep;
	
	@Column(name = "endereco_logradouro")
	private String logradouro;
	
	@Column(name = "endereco_numero")
	private String numero;
	
	@Valid
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
}

package com.matheussilvadev.pessoas.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.matheussilvadev.pessoas.Groups;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@ApiModelProperty(example = "45609500")
	@Column(name = "endereco_cep")
	private String cep;
	
	@ApiModelProperty(example = "Rua XPTO")
	@Column(name = "endereco_logradouro")
	private String logradouro;
	
	@ApiModelProperty(example = "32")
	@Column(name = "endereco_numero")
	private String numero;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CidadeId.class)
	@ManyToOne
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade;
}

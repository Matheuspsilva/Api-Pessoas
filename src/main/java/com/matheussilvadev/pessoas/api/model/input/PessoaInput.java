package com.matheussilvadev.pessoas.api.model.input;

import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaInput {

	@ApiModelProperty(example = "Letícia Abreu")
	@NotBlank
	private String nome;

	@NotNull
	private OffsetDateTime dataNascimento;

	@Valid
	@Embedded
	private EnderecoInput endereco;

}

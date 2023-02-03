package com.matheussilvadev.pessoas.api.model.input;

import java.time.LocalDateTime;

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
	private LocalDateTime dataNascimento;

	@Valid
	@Embedded
	private EnderecoInput endereco;

}

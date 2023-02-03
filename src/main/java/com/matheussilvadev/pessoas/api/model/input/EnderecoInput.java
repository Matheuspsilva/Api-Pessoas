package com.matheussilvadev.pessoas.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {
	
	@ApiModelProperty(example = "45609500")
	@NotBlank
	private String cep;

	@ApiModelProperty(example = "Rua XPTO")
	@NotBlank
	private String logradouro;

	@ApiModelProperty(example = "32")
	@NotBlank
	private String numero;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

}

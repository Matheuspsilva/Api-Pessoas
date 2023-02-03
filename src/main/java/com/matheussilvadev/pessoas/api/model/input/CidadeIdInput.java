package com.matheussilvadev.pessoas.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInput {

	@ApiModelProperty(example = "Belo Horizonte")
	@NotNull
	private Long id;

}
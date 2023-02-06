package com.matheussilvadev.pessoas.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {
	
	@ApiModelProperty(example = "1")
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(example = "Letícia Abreu")
	@NotBlank
    @Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataNascimento;
	
	@Valid
	@Embedded
	private Endereco endereco;

	
}

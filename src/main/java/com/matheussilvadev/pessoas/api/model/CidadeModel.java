package com.matheussilvadev.pessoas.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeModel {
	
	private String id;
	private String nome;
	private EstadoModel estado;

}

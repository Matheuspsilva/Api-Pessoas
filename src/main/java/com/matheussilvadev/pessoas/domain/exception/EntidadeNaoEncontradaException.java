package com.matheussilvadev.pessoas.domain.exception;

public abstract class EntidadeNaoEncontradaException extends EntidadeEmUsoException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}

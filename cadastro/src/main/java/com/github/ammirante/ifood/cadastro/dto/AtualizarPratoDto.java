package com.github.ammirante.ifood.cadastro.dto;

import java.io.Serializable;

/**
 * AtualizarPratoDto
 *
 */
public class AtualizarPratoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7860001516826682877L;
	
	private String nome;
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}

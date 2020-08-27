package com.github.ammirante.ifood.cadastro.dto;

import java.io.Serializable;

/**
 * AtualizarRestauranteDto
 *
 */
public class AtualizarRestauranteDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1951876794590550707L;
	
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

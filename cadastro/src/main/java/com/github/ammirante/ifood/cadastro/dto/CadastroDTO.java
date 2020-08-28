package com.github.ammirante.ifood.cadastro.dto;

import java.io.Serializable;

import com.github.ammirante.ifood.cadastro.util.ToString;

/**
 * Classe padrão para utilização de DTO.
 *
 */
public class CadastroDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2055796904694797729L;

	/**
	 *
	 */
	public String toString() {
		return ToString.build(this);
	}
}

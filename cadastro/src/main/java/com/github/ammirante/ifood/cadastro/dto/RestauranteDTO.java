package com.github.ammirante.ifood.cadastro.dto;

import java.util.Date;

/**
 * RestauranteDTO
 *
 */
public class RestauranteDTO extends CadastroDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7023482325745836939L;
	
	public Long id;
	public String proprietario;
	public String cnpj;
	public String nome;
	public LocalizacaoDTO localizacaoDTO; 
	public Date dataCriacao;
	
}

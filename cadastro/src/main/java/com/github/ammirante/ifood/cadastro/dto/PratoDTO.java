package com.github.ammirante.ifood.cadastro.dto;

/**
 * PratoDTO
 *
 */
public class PratoDTO extends CadastroDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -608004667868887716L;
	
	public Long id;
	public String nome;
	public String descricao;
	public String preco;
	public RestauranteDTO restaurante;
	
}

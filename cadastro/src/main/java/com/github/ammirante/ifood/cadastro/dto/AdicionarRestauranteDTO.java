package com.github.ammirante.ifood.cadastro.dto;

/**
 * AdicionarRestauranteDTO
 *
 */
public class AdicionarRestauranteDTO extends CadastroDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689633935434853053L;
	
	public String proprietario;
	public String cnpj;
	public String nomeFantasia;
	public LocalizacaoDTO localizacaoDTO;
	
}

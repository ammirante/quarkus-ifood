package com.github.ammirante.ifood.cadastro.dto;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.github.ammirante.ifood.cadastro.entidade.Restaurante;
import com.github.ammirante.ifood.cadastro.infra.DTO;
import com.github.ammirante.ifood.cadastro.infra.ValidDTO;

/**
 * AdicionarRestauranteDTO
 *
 */
@ValidDTO
public class AdicionarRestauranteDTO extends CadastroDTO implements DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689633935434853053L;
	
	@NotEmpty
	@NotNull
	public String proprietario;
	
	@NotEmpty
	@NotNull
	@Pattern(regexp = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}")
	public String cnpj;
	
	@Size(min = 3, max = 30)
	public String nomeFantasia;
	
	public LocalizacaoDTO localizacaoDTO;
	
    /**
     *
     */
    @Override
    public Boolean isValid(ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Restaurante.find("cnpj", cnpj).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado.")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation()
                    ;
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
    }
}

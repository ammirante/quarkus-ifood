/**
 * 
 */
package com.github.ammirante.ifood.cadastro.infra;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * ConstraintViolationImpl
 *
 */
public class ConstraintViolationImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -577814040649703085L;
	
    @Schema(description = "Path do atributo, ex: dataInicio, pessoa.endereco.numero", required = false)
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path", required = true)
    private final String mensagem;

    /**
     * @param violation
     */
    private ConstraintViolationImpl(ConstraintViolation<?> violation) {
        this.mensagem = violation.getMessage();
        this.atributo = Stream.of(violation.getPropertyPath().toString().split("\\.")).skip(2).collect(Collectors.joining("."));
    }

    /**
     * @param atributo
     * @param mensagem
     */
    public ConstraintViolationImpl(String atributo, String mensagem) {
        this.mensagem = mensagem;
        this.atributo = atributo;
    }

    /**
     * @param violation
     * @return
     */
    public static ConstraintViolationImpl of(ConstraintViolation<?> violation) {
        return new ConstraintViolationImpl(violation);
    }

    /**
     * @param violation
     * @return
     */
    public static ConstraintViolationImpl of(String violation) {
        return new ConstraintViolationImpl(null, violation);
    }

    /**
     * @return
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @return
     */
    public String getAtributo() {
        return atributo;
    }

}

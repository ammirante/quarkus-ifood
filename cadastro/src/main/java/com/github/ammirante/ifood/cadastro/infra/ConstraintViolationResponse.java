/**
 * 
 */
package com.github.ammirante.ifood.cadastro.infra;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

/**
 * ConstraintViolationResponse
 *
 */
public class ConstraintViolationResponse {
	

    private final List<ConstraintViolationImpl> violacoes = new ArrayList<>();

    /**
     * @param exception
     */
    private ConstraintViolationResponse(ConstraintViolationException exception) {
        exception.getConstraintViolations().forEach(violation -> violacoes.add(ConstraintViolationImpl.of(violation)));
    }

    /**
     * @param exception
     * @return
     */
    public static ConstraintViolationResponse of(ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }

    /**
     * @return
     */
    public List<ConstraintViolationImpl> getViolacoes() {
        return violacoes;
    }
}

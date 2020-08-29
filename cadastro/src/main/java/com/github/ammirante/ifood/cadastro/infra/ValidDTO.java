/**
 * 
 */
package com.github.ammirante.ifood.cadastro.infra;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * ValidDTO
 *
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidDTOValidator.class})
@Documented
public @interface ValidDTO {
	
    /**
     * @return
     */
    String message() default "{com.github.ammirante.ifood.cadastro.infra.ValidDTO.message}";
    
    /**
     * @return
     */
    Class<?>[] groups() default {};
    
    /**
     * @return
     */
    Class<? extends Payload>[] payload() default {};

}

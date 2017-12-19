package br.com.sgt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 13/12/2017
 *
 */
public class Validador {

    private final Map<String, String> erros;
    private final Validator validation;

    public Validador() {
        this.erros = new HashMap<>();
        this.validation = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Boolean isValido(Object object) {
        return this.validation.validate(object).isEmpty();
    }

    public Map<String, String> validar(Object object) {

        erros.clear();
        Set<ConstraintViolation<Object>> constraintViolations = this.validation.validate(object);

        constraintViolations.stream().forEach((constraintViolation) -> {
            this.erros.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessageTemplate());
        });

        return this.erros;
    }

}

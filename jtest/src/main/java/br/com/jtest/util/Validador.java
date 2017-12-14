package br.com.jtest.util;

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

    private Map<String, String> erros;

    public static Boolean isValido(Object object) {
        return getValidator().validate(object).isEmpty();
    }

    public static Map<String, String> validar(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = getValidator().validate(object);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
//            erros.put(constraintViolation.getPropertyPath(), constraintViolation.getMessage());
        }

        return null;
    }

    private static Validator getValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}

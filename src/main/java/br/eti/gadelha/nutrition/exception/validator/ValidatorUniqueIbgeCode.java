package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueIbgeCode;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFood;
import br.eti.gadelha.nutrition.service.ServiceFood;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorUniqueIbgeCode implements ConstraintValidator<UniqueIbgeCode, DTORequestFood> {

    @Autowired
    private ServiceFood serviceFood;

    @Override
    public void initialize(UniqueIbgeCode constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestFood value, ConstraintValidatorContext context) {
        if (value != null && value.getId() == null && !serviceFood.existsByName(value.getName()) ||
                value != null && value.getId() != null && value.getName() != null && serviceFood.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
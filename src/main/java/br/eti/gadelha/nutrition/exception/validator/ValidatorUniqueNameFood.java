package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueNameFood;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFood;
import br.eti.gadelha.nutrition.service.ServiceFood;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.isNull;

public class ValidatorUniqueNameFood implements ConstraintValidator<UniqueNameFood, DTORequestFood> {

    @Autowired
    private ServiceFood serviceFood;

    @Override
    public void initialize(UniqueNameFood constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestFood value, ConstraintValidatorContext context) {
        if (!isNull(value.getName()) && !serviceFood.existsByName(value.getName()) ||
            !isNull(value.getName()) && !isNull(value.getId()) && serviceFood.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
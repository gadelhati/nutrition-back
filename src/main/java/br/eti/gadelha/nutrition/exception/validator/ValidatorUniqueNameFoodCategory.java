package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueNameFoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFoodCategory;
import br.eti.gadelha.nutrition.service.ServiceFoodCategory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.isNull;

public class ValidatorUniqueNameFoodCategory implements ConstraintValidator<UniqueNameFoodCategory, DTORequestFoodCategory> {

    @Autowired
    private ServiceFoodCategory serviceFoodCategory;

    @Override
    public void initialize(UniqueNameFoodCategory constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestFoodCategory value, ConstraintValidatorContext context) {
        if (!isNull(value.getName()) && !serviceFoodCategory.existsByName(value.getName()) ||
                !isNull(value.getName()) && !isNull(value.getId()) && serviceFoodCategory.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
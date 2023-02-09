package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueNameRole;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;
import br.eti.gadelha.nutrition.service.ServiceRole;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.isNull;

public class ValidatorUniqueNameRole implements ConstraintValidator<UniqueNameRole, DTORequestRole> {

    @Autowired
    private ServiceRole serviceRole;

    @Override
    public void initialize(UniqueNameRole constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestRole value, ConstraintValidatorContext context) {
        if (!isNull(value.getName()) && !serviceRole.existsByName(value.getName()) ||
                !isNull(value.getName()) && !isNull(value.getId()) && !serviceRole.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
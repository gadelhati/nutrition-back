package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueNamePrivilege;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.service.ServicePrivilege;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.isNull;

public class ValidatorUniqueNamePrivilege implements ConstraintValidator<UniqueNamePrivilege, DTORequestPrivilege> {

    @Autowired
    private ServicePrivilege servicePrivilege;

    @Override
    public void initialize(UniqueNamePrivilege constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestPrivilege value, ConstraintValidatorContext context) {
        if (!isNull(value.getName()) && !servicePrivilege.existsByName(value.getName()) ||
                !isNull(value.getName()) && !isNull(value.getId()) && servicePrivilege.existsByNameAndIdNot(value.getName(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
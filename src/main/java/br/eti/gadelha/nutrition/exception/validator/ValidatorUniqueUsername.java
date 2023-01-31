package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.UniqueUsername;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestUserEntity;
import br.eti.gadelha.nutrition.service.ServiceUserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorUniqueUsername implements ConstraintValidator<UniqueUsername, DTORequestUserEntity> {

    @Autowired
    private ServiceUserEntity serviceUserEntity;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }
    @Override
    public boolean isValid(DTORequestUserEntity value, ConstraintValidatorContext context) {
        if (value != null && value.getId() == null && !serviceUserEntity.existsByName(value.getUsername()) ||
                value != null && value.getId() != null && value.getUsername() != null && serviceUserEntity.existsByNameAndIdNot(value.getUsername(), value.getId()) ) {
            return true;
        } else {
            return false;
        }
    }
}
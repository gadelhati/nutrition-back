package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.GenericAnnotation;
import br.eti.gadelha.nutrition.service.ServiceFood;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.isNull;

public abstract class GenericValidator<T> implements ConstraintValidator<GenericAnnotation, T> {

    @Autowired
    private ServiceFood serviceFood;

    @Override
    public void initialize(GenericAnnotation constraintAnnotation) {
    }
    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        if (value != null) {
            return true;
        } else {
            return false;
        }
//        if (!isNull(value.getName()) && !serviceFood.existsByIbgeCode(value.getName()) ||
//                !isNull(value.getName()) && !isNull(value.getId()) && !serviceFood.existsByIbgeCodeAndIdNot(value.getName(), value.getId()) ) {
//            return true;
//        } else {
//            return false;
//        }
    }
    protected abstract Object toLocalDate(T value);
}
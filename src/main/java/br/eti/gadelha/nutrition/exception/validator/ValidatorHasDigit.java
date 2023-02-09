package br.eti.gadelha.nutrition.exception.validator;

import br.eti.gadelha.nutrition.exception.annotation.HasNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static br.eti.gadelha.nutrition.exception.validator.Validator.hasDigit;

public class ValidatorHasDigit implements ConstraintValidator<HasNumber, String> {

    @Override
    public void initialize(HasNumber constraintAnnotation) {
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (hasDigit(value)) {
            return true;
        } else {
            return false;
        }
    }
}
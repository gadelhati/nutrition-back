package br.eti.gadelha.nutrition.exception.annotation;

import br.eti.gadelha.nutrition.exception.validator.DTORequestValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })// METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DTORequestValidator.class })
@Documented
public @interface GenericAnnotation {

    String message() default "{unique}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
//    DayOfWeek[] days() default {DayOfWeek.MONDAY};
}
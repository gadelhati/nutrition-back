package br.eti.gadelha.nutrition.exception.annotation;

import br.eti.gadelha.nutrition.exception.validator.ValidatorUniqueIbgeCode;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidatorUniqueIbgeCode.class })
@Documented
public @interface UniqueIbgeCode {

    String message() default "{unique.ibge.code}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
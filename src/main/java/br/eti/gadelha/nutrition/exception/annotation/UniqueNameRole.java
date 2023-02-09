package br.eti.gadelha.nutrition.exception.annotation;

import br.eti.gadelha.nutrition.exception.validator.ValidatorUniqueNameRole;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidatorUniqueNameRole.class })
@Documented
public @interface UniqueNameRole {

    String message() default "{unique.name.role}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
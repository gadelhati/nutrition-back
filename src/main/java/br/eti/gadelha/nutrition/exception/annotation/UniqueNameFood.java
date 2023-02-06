package br.eti.gadelha.nutrition.exception.annotation;

import br.eti.gadelha.nutrition.exception.validator.ValidatorUniqueNameFood;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidatorUniqueNameFood.class })
@Documented
public @interface UniqueNameFood {

    String message() default "{unique.name.food}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
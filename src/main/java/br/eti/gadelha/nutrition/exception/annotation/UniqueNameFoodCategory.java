package br.eti.gadelha.nutrition.exception.annotation;

import br.eti.gadelha.nutrition.exception.validator.ValidatorUniqueNameFoodCategory;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidatorUniqueNameFoodCategory.class })
@Documented
public @interface UniqueNameFoodCategory {

    String message() default "{unique.name.food.category}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
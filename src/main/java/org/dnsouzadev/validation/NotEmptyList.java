package org.dnsouzadev.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.dnsouzadev.validation.constraintvalidation.NotEmptyListValidation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidation.class)
public @interface NotEmptyList {
    String message() default "A lista nao pode ser vazia";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

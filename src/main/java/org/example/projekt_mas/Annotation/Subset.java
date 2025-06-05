package org.example.projekt_mas.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SubsetValidator.class)
@Documented
public @interface Subset {
    String message() default "{org.example.projekt_mas.Annotation.Subset.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

package org.example.projekt_mas.Annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.projekt_mas.model.Zmiana;

public class SubsetValidator implements ConstraintValidator<Subset, Zmiana> {
    @Override
    public void initialize(Subset constraintAnnotation) {
    }

    @Override
    public boolean isValid(Zmiana zmiana, ConstraintValidatorContext constraintValidatorContext) {
        return zmiana.getPracownicy().contains(zmiana.getMenadzer().getPracownik());
    }
}

package com.doncorleone.dondelivery.resources.exceptions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    private String REGEX_TELEPHONE = "([0-9]{3}|[0-9]{2})?([0-9]{2})([0-9]{4,5})([0-9]{4})";

    @Override
    public void initialize(Telephone telephoneNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext context) {
        return contactField != null && contactField.matches(REGEX_TELEPHONE)
                && (contactField.length() > 8) && (contactField.length() < 14);
    }


}

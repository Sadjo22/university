package com.pns.university.utils;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailValidation {
    public static boolean isValidEmailAddress(String emailAdress) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(emailAdress);
    }
}

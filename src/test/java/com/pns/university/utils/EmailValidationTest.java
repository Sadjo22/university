package com.pns.university.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EmailValidationTest {
    @Test
    void itShouldIsValidEmailAddress() {

        //Given
        String emailAdress = "peguynya@gmail.com";
        boolean isValidEmail = false;
        //When && Then
        isValidEmail = EmailValidation.isValidEmailAddress(emailAdress);
        assertTrue(isValidEmail);
    }

    @Test
    void itShouldNotValidatedEmailAddressWithoutDomain() {

        //Given
        String emailAdress = "peguynya@";
        boolean isValidEmail = false;
        //When && Then
        isValidEmail = EmailValidation.isValidEmailAddress(emailAdress);
        assertFalse(isValidEmail);
    }
    @Test
    void itShouldNotValidatedEmailAddressWithoutChiocciola() {

        //Given
        String emailAdress = "peguynyagmail.com";
        boolean isValidEmail = false;
        //When && Then
        isValidEmail = EmailValidation.isValidEmailAddress(emailAdress);
        assertFalse(isValidEmail);
    }
    @Test
    void itShouldNotValidatedEmailAddressWithoutLocalPart() {

        //Given
        String emailAdress = "@gmail.com";
        boolean isValidEmail = false;
        //When && Then
        isValidEmail = EmailValidation.isValidEmailAddress(emailAdress);
        assertFalse(isValidEmail);
    }
    @Test
    void itShouldNotValidatedEmailAddressWithoutPunto() {

        //Given
        String emailAdress = "peguynya@gmailcom";
        boolean isValidEmail = false;
        //When && Then
        isValidEmail = EmailValidation.isValidEmailAddress(emailAdress);
        assertFalse(isValidEmail);
    }
}
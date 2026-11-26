package com.booking;

import org.junit.Test;

public class UserValidatorTest {
    @Test 
    public void validateNameWithCorrectName() {
        UserValidator.validateName("Dmitry");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNamewithEmptyName() {
        UserValidator.validateE
    }
}

package com.example.csse_project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardHelperTest {

    @Test
    void getTokenid() {
        CreditCardHelper u = new CreditCardHelper("fMHJrmwXcATy5QvoHTnCvbfT3ce2");
        assertEquals("fMHJrmwXcATy5QvoHTnCvbfT3ce2",u.getTokenid());
    }

    @Test
    void setTokenid() {
        fail();
    }
}
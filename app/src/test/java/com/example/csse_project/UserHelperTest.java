package com.example.csse_project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserHelperTest {

    @Test
    void getBalance() {

        UserHelper u = new UserHelper(100);
        assertEquals(100,u.getBalance());
    }

    @org.junit.jupiter.api.Test
    void setBalance() {
        fail();
    }

    @Test
    void getName() {
        UserHelper u = new UserHelper("Eeswar");
        assertEquals("Eeswar",u.getName());
    }

    @Test
    void setName() {
        fail();
    }
}
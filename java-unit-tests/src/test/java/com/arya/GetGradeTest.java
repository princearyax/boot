package com.arya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetGradeTest {

    private Calc calc;

    //@BeforeEach : as the name, runs b4 ecah
    //used to reset the states so that the tests don't interfer with each other

    @BeforeEach
    void setUp(){
        calc = new Calc();
    }

    @Test
    void testGradeA(){
        String res = calc.getGrade(95);
        //arguments will go like (Expected, Actual, "Message to show if it fail")
        assertEquals("A", res, "Should be A"); 
    }

    @Test
    void testGradeBBoundary(){
        String res = calc.getGrade(80);
        assertEquals("B", res, " should be B");
    }

    @Test
    void testFail(){
        assertEquals("F", calc.getGrade(40));
    }

    @Test
    void testInvalidInput(){
        //testing exceptione be like...
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.getGrade(105);
        });
        assertEquals("Score must be between 0 and 10", exception.getMessage());
    }
}

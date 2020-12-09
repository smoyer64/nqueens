package com.selesy.nqueens.solvers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RuleTests {

    @Test
    void testToString() {
        assertEquals("Diagonal", Rule.DIAGONAL.toString());
    }

}

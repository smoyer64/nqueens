package com.selesy.nqueens.solvers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rule")
public class RuleTests {

    @DisplayName("String toString()")
    @Test
    void testToString() {
        assertEquals("Diagonal", Rule.DIAGONAL.toString());
    }

}

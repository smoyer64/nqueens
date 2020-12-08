package com.selesy.nqueens.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Square.java")
public class SquareTests {

    static Map<Integer, String> failures = new HashMap<>();

    @BeforeAll
    static void setupAll() {
        failures.put(0, Square.MESSAGE_FILE_RANK_INDEX);
        failures.put(1, Square.MESSAGE_NOTATION_FORMAT);
    }

    /*
     * Tests the ways that both the fromString method and the constructor can fail.
     */
    @DisplayName("Failure")
    @ParameterizedTest
    @CsvSource({ "`10, 0", // file is too low
            "{10, 0", // file is too high
            "a-1, 0", // rank is too low
            "a27, 0", // rank is too high
            "abc, 1", // rank is not a number
            "a, 1", // notation is too short
    })
    void testFromStringAndConstructorFailures(String notation, int failure) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> Square.fromString(notation));
        assertTrue(e.getMessage()
                    .startsWith(failures.get(failure)));
    }

    /*
     * Tests both the fromString and toString methods by round-tripping an algebraic
     * notation. Note that this test also exercises the constructor with valid
     * input.
     */
    @DisplayName("Success")
    @ParameterizedTest
    @CsvSource({ "0, 0, a1", "0, 7, a8", "7, 0, h1", "7, 7, h8", "25, 25, z26" })
    void testFromStringAndToString(int file, int rank, String notation) {
        Square s = Square.fromString(notation);
        assertEquals(file, s.getFile());
        assertEquals(rank, s.getRank());
        assertEquals(notation, (new Square(file, rank)).toString());
    }
}

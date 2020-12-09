package com.selesy.nqueens.solvers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import com.selesy.nqueens.board.Square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("NQueensBoard")
public class NQueensBoardTests {

    @DisplayName("IsValid()")
    @ParameterizedTest(name = "{index} - Queen positions: {0}")
    @CsvSource({
    // @formatter:off
    "3, 'a1, a3'",    // Vertical violation
    "3, 'a1, c1'",    // Horizontal violation
    "3, 'a1, c3'",    // Diagonal violation
    "5, 'a1, b3, c5'" // Triplet violation
    // @formatter:on
    })
    void testIsValid(int size, String positions) {
        NQueensBoard board = new NQueensBoard(size);
        for (Square square : squares(positions)) {
            board.putDown(square);
        }

        assertFalse(board.isValid());
    }

    @DisplayName("IsValid(Square)")
    @ParameterizedTest(name = "{index} - Queen positions: {1}, Rule: {2}")
    @CsvSource({
    // @formatter:off
    "3, 'a1, a3', VERTICAL",    // Vertical violation
    "3, 'a1, c1', HORIZONTAL",    // Horizontal violation
    "3, 'a1, c3', DIAGONAL",    // Diagonal violation
    "5, 'a1, b3, c5', TRIPLET" // Triplet violation
    // @formatter:on
    })
    void testIsValidWithSquare(int size, String positions, Rule rule) {
        NQueensBoard board = new NQueensBoard(size);
        List<Square> squares = squares(positions);

        for (Square square : squares(positions)) {
            board.putDown(square);
        }

        Square last = squares.get(squares.size() - 1);
        List<Violation> violations = board.violations()
                                          .get(last);

        // The number of violations will be equal to the number of
        // positions minus one since the last queen is tested against
        // each of the others.
        assertEquals(squares.size() - 1, violations.size());
        Violation violation = violations.get(0);
        assertEquals(rule, violation.rule);
    }

    List<Square> squares(String positions) {
        List<Square> pieces = new ArrayList<>();

        if (positions.isBlank()) {
            return pieces;
        }

        positions = positions.replaceAll(" ", "");
        for (String position : positions.split(",")) {
            pieces.add(Square.fromString(position));
        }

        return pieces;
    }
}

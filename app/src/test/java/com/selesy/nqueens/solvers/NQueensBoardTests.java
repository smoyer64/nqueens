package com.selesy.nqueens.solvers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.selesy.nqueens.board.Square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("NQueensBoard")
public class NQueensBoardTests {

    @DisplayName("NQueensBoard(Int) - Illegal board size")
    @ParameterizedTest(name = "{index} - Board size: {0}")
    @ValueSource(ints = { 0, 27 })
    void testConstructorWithIllegalBoardSize(int size) {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new NQueensBoard(size);
        });

        assertTrue(e.getMessage()
                    .startsWith(NQueensBoard.MESSAGE_ILLEGAL_BOARD_SIZE));
    }

    @DisplayName("isValid()")
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
        board.load(positions);

        assertFalse(board.isValid());
    }

    @DisplayName("isValid(Square)")
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
        board.load(positions);

        List<Square> squares = board.getQueens();
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

    @DisplayName("load(String)")
    @ParameterizedTest(name = "{index} - Positions: {1}, Expected size: {0}")
    @CsvSource({
    // @formatter:off
        "0, ''",
        "3, 'a1, b3, c5'"
        // @formatter:on
    })
    void testLoadFromAlgebraicNotation(int count, String positions) {
        NQueensBoard board = new NQueensBoard(3);
        board.load(positions);
        List<Square> queens = board.getQueens();

        assertEquals(count, queens.size());
    }
}

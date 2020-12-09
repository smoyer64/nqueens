package com.selesy.nqueens.solvers.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.selesy.nqueens.board.Square;
import com.selesy.nqueens.solvers.Solver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("BacktrackingSolver")
public class BacktrackingSolverTests {

    @DisplayName("Solve")
    @ParameterizedTest(name = "{index} - Chessboard size: {0}, Queen positions: {1}")
    @CsvSource({
    // @formatter:off
        "1, 'a1'",
        "2, ''",
        "3, ''",
        "4, 'a2, b4, c1, d3'",
        "5, ''",
        "6, ''",
        "7, ''",
        "8, 'a3, b5, c8, d4, e1, f7, g2, h6'",
        "9, 'a1, b7, c4, d6, e9, f2, g5, h3, i8'",
        "10, 'a1, b4, c9, d5, e8, f10, g3, h6, i2, j7'"
    // @formatter:on
    })
    void testSolve(int size, String positions) {
        Solver solver = new BacktrackingSolver(size);
        List<Square> exp = expectedPieces(positions);
        assertEquals(exp, solver.solve()
                                .get());
    }

    List<Square> expectedPieces(String positions) {
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

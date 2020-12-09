package com.selesy.nqueens.solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.selesy.nqueens.board.BaseBoard;
import com.selesy.nqueens.board.Board;
import com.selesy.nqueens.board.Square;

public class NQueensBoard implements Board {

    public static String MESSAGE_ILLEGAL_BOARD_SIZE = "Illegal board size requested (" + BaseBoard.MIN_DIMENSIONS + "-" + BaseBoard.MAX_DIMENSIONS + ")";

    // Dimensions of this chessboard.
    int size;

    // Queens that have been previously placed on the board.
    List<Square> queens = new ArrayList<>();

    // Indicates positions that cannot be used due to the rule stating that three
    // queens may not be in a straight line (Rule.TRIPLET).
    Set<Square> blocked = new HashSet<>();

    public NQueensBoard(int size) throws IllegalArgumentException {
        if (size < BaseBoard.MIN_DIMENSIONS || size > BaseBoard.MAX_DIMENSIONS) {
            throw new IllegalArgumentException(String.format(MESSAGE_ILLEGAL_BOARD_SIZE + " - got: %d", BaseBoard.MAX_DIMENSIONS, size));
        }

        this.size = size;
    }

    // Calculates (or recalculates) the blocked positions on the chessboard.
    Set<Square> blocked() {
        // Two queens are required to define the initial vector
        if (queens.size() < 2) {
            return new HashSet<Square>();
        }

        // Clone the list of queens so we can recurse over head+tail
        List<Square> q = new ArrayList<>();
        for (Square queen : queens) {
            q.add(new Square(queen.getFile(), queen.getRank()));
        }

        // Split head+tail and recurse
        Square head = q.remove(0);
        return blocked(head, q);
    }

    // Recursively calculates the blocked positions on the chessboard.
    // E.g. For a 5x5 chessboard, 5 queens are allowed so this calculates
    // squares on the vectors of 5+4+3+2 pairs of queens.
    Set<Square> blocked(Square queen, List<Square> queens) {
        Set<Square> b = new HashSet<>();

        // Block squares in vector for each tail queen
        for (Square q : queens) {
            int dx = q.getFile() - queen.getFile();
            int dy = q.getRank() - queen.getRank();
            int x = q.getFile() + dx;
            int y = q.getRank() + dy;
            while (x >= 0 && x < size && y >= 0 && y < size) {
                b.add(new Square(x, y));
                x += dx;
                y += dy;
            }
        }

        // Recurse if needed
        if (queens.size() >= 2) {
            Square head = queens.remove(0);
            b.addAll(blocked(head, queens));
        }

        return b;
    }

    public void clear() {
        queens.clear();
        blocked.clear();
    }

    public List<Square> getQueens() {
        return queens;
    }

    public boolean isValid(char file, int rank) {
        return violations(file, rank).size() == 0;
    }

    public boolean isValid(Square queen) {
        return violations(queen).size() == 0;
    }

    public boolean isValid() {
        return violations().size() == 0;
    }

    public void load(List<Square> squares) {
        for (Square square : squares) {
            putDown(square);
        }
    }

    public void load(String positions) {
        if (positions == null || positions.isEmpty()) {
            clear();
            return;
        }

        List<Square> squares = new ArrayList<>();

        positions = positions.replaceAll(" ", "");
        for (String position : positions.split(",")) {
            squares.add(Square.fromString(position));
        }

        load(squares);
    }

    public Square pickUp() {
        Square q = queens.remove(queens.size() - 1);
        pickUp(q);
        return q;
    }

    public void pickUp(Square queen) {
        queens.remove(queen);
        blocked = blocked();
    }

    public void putDown(Square queen) {
        queens.add(queen);
        blocked = blocked();
    }

    public int size() {
        return size;
    }

    public List<Violation> violations(char file, int rank) {
        return violations(new Square(file, rank));
    }

    public List<Violation> violations(Square queen) {
        List<Violation> violations = new ArrayList<>();
        for (Square q : queens) {
            // Don't validate the target queen against itself
            if (q.equals(queen)) {
                continue;
            }

            // Vertical (same file)
            if (q.getFile() == queen.getFile()) {
                violations.add(new Violation(q, queen, Rule.VERTICAL));
                continue;
            }

            // Horizontal (same rank)
            if (q.getRank() == queen.getRank()) {
                violations.add(new Violation(q, queen, Rule.HORIZONTAL));
                continue;
            }

            // Diagonal
            if (Math.abs(q.getFile() - queen.getFile()) == Math.abs(q.getRank() - queen.getRank())) {
                violations.add(new Violation(q, queen, Rule.DIAGONAL));
                continue;
            }

            // Triplet
            if (blocked.contains(queen)) {
                violations.add(new Violation(q, queen, Rule.TRIPLET));
            }
        }

        return violations;
    }

    public Map<Square, List<Violation>> violations() {
        Map<Square, List<Violation>> violations = new HashMap<>();
        for (Square q : queens) {
            violations.put(q, violations(q));
        }
        return violations;
    }
}

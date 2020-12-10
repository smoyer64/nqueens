package com.selesy.nqueens.solvers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.selesy.nqueens.board.BaseBoard;
import com.selesy.nqueens.board.Board;
import com.selesy.nqueens.board.Color;
import com.selesy.nqueens.board.Piece;
import com.selesy.nqueens.board.Square;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Provides an implementation of a chessboard that tuned for the N-Queens
 * solvers. It only stores queens but when each is placed, a list of blocked
 * squares is calculated to ensure "3-in-a-row" violations can be found easily.
 */
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

    /**
     * Clears the queens from the chessboard as well as clearing the array of
     * squares blocked by the "3-in-a-row" rule.
     */
    public void clear() {
        queens.clear();
        blocked.clear();
    }

    /**
     * Returns the list of queens currently placed on the chessboard.
     * 
     * @return the queens.
     */
    public List<Square> getQueens() {
        return queens;
    }

    /**
     * Given a file and a rank, returns a boolean indicating whether a queen can be
     * placed at that position without violating a rule.
     * 
     * @param file
     *                 the file.
     * @param rank
     *                 the rank.
     * @return true if valid, otherwise false.
     */
    public boolean isValid(char file, int rank) {
        return violations(file, rank).size() == 0;
    }

    /**
     * Given a Square, returns a boolean indicating whether a queen can be placed at
     * that position without violating a rule.
     * 
     * @return true if valid, otherwise false.
     */
    public boolean isValid(Square queen) {
        return violations(queen).size() == 0;
    }

    /**
     * Indicates whether the queens currently placed on the chessboard violate any
     * of the rules.
     * 
     * @return true if valid, otherwise false.
     */
    public boolean isValid() {
        return violations().size() == 0;
    }

    /**
     * Places each of the queens whose positions are represented by the provided
     * squares onto the board.
     */
    public void load(List<Square> squares) {
        for (Square square : squares) {
            putDown(square);
        }
    }

    /**
     * Parse a comma-separated list of the queens' positions from algebraic notation
     * and places them each on the chessboard.
     */
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

    /**
     * Removes the last queen placed on the board from its position and returns it
     * to the caller.
     * 
     * @return the Square.
     */
    public Square pickUp() {
        Square q = queens.remove(queens.size() - 1);
        pickUp(q);
        return q;
    }

    /**
     * Removes the queen matching the passed Square from the chessboard.
     */
    public void pickUp(Square queen) {
        queens.remove(queen);
        blocked = blocked();
    }

    /**
     * Places the queen represented by the Square's position on the chessboard and
     * recalculates the blocked positions.
     */
    public void putDown(Square queen) {
        queens.add(queen);
        blocked = blocked();
    }

    /**
     * Returns the dimensions of the chessboard.
     * 
     * @return the dimensions.
     */
    public int size() {
        return size;
    }

    /**
     * Given a position on the chessboard as a file/rank pair, returns a list of
     * violations for that position.
     * 
     * @param file
     *                 the file (column).
     * @param rank
     *                 the rank (row).
     * @return a list of violations.
     */
    public List<Violation> violations(char file, int rank) {
        return violations(new Square(file, rank));
    }

    /**
     * Given a position on the chessboard as a Square, returns a list of violations
     * for that position.
     * 
     * @param queen
     *                  the queen's position.
     * @return a list of violations.
     */
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

    /**
     * Returns a map containing a list of the violations for each and every square
     * on the chessboard.
     * 
     * @return the violations.
     */
    public Map<Square, List<Violation>> violations() {
        Map<Square, List<Violation>> violations = new HashMap<>();
        for (Square q : queens) {
            violations.put(q, violations(q));
        }
        return violations;
    }

    /**
     * Returns a diagram of the chessboard shaded using ANSI characters
     */
    public String toString() {
        // Map the files using rank as a key
        Map<Integer, Integer> qs = new HashMap<>();
        for (Square q : queens) {
            qs.put(q.getRank(), q.getFile());
        }

        // Determine whether the board needs flipped to end up with a
        // white square on the lower right corner.
        int flip = queens.size() % 2;
        // Get the unicode value for a black queen.
        char sym = Piece.QUEEN.getSymbol(Color.BLACK);

        StringBuilder sb = new StringBuilder();
        for (int r = queens.size() - 1; r >= 0; r--) {

            // put the rank to the left of each row
            sb.append(String.format("%2d ", r + 1));
            for (int f = 0; f < queens.size(); f++) {

                // out is either a queen or space
                String out = "   ";
                if (qs.get(r) == f) {
                    out = " " + sym + " ";
                }

                // Set up the correct background
                if (((f + r + flip) % 2) == 0) {
                    sb.append(ansi().bg(BLACK));
                } else {
                    sb.append(ansi().bgBright(BLACK));
                }

                // Write the square
                sb.append(ansi().fg(WHITE)
                                .a(out));
            }
            sb.append(ansi().reset()
                            .a("\n"));
        }

        // puts the file under each column
        sb.append("   ");
        for (int f = 0; f < queens.size(); f++) {
            sb.append(String.format(" %c ", (char) (f + (int) 'a')));
        }
        sb.append("\n");

        return sb.toString();
    }
}

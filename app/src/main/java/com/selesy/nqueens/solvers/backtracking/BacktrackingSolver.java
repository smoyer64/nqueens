package com.selesy.nqueens.solvers.backtracking;

import java.util.List;
import java.util.Optional;

import com.selesy.nqueens.board.Square;
import com.selesy.nqueens.solvers.NQueensBoard;
import com.selesy.nqueens.solvers.Solver;

/**
 * Provides an Solver implementation that uses backtracking to avoid getting
 * "trapped in corners".
 */
public class BacktrackingSolver implements Solver {

    NQueensBoard board;

    /**
     * Constructs a BacktrackingSolver with a chessboard size x size dimensions and
     * that attempts to place "size" queens.
     * 
     * @param size
     *                 the dimensions of the chessboard and target number of queens.
     */
    public BacktrackingSolver(int size) {
        board = new NQueensBoard(size);
    }

    /**
     * Attempts to find a solution to the modified N-Queens puzzle.
     * 
     * @return an Optional containing the solution if one is found.
     */
    public Optional<List<Square>> solve() {
        // this square is being used to move around the chessboard
        // (essentially a file/rank pair).
        Square pos = new Square(0, 0);

        do {
            // if there's a valid square at pos, place a queen and
            // continue with rank zero of the next file. If all
            // queens have been placed, then break.
            if (board.isValid(pos)) {
                if (place(pos)) {
                    break;
                }
                continue;
            }

            // try the next square (forward)
            forward(pos);

            // at the last rank for the file then (recursively) backtrack
            if (pos.getRank() >= board.size()) {
                pos = backtrack(board.pickUp());
            }

            // after backtracking as far as possible, the file will be
            // zero (a) and the rank will be equal to the size of the
            // board so no additional positions are possible.
        } while (pos.getRank() < board.size());

        return Optional.of(board.getQueens())
                       .map(o -> o.size() == 0 ? null : o);
    }

    /*
     * Recursively backtracks through the already placed queens trying to find one
     * that can still have its rank increased without exceeding the boundaries of
     * the board. If the square's file is zero, no more recursion is possible so
     * that square is returned with an invalid rank (indicating there's no
     * solution).
     * 
     * @param pos the next candidate square.
     * 
     * @return the next valid square to try.
     */
    Square backtrack(Square pos) {
        // If the square is itself in the highest rank, try the next one
        if (pos.getRank() >= board.size() - 1 && pos.getFile() > 0) {
            return backtrack(board.pickUp());
        }

        // Once a available square is found, increase its rank
        pos.setRank(pos.getRank() + 1);
        return pos;
    }

    /*
     * Moves forward to the next square on the chessboard.
     * 
     * @param pos the current position on the chessboard.
     */
    void forward(Square pos) {
        pos.setRank(pos.getRank() + 1);
    }

    /*
     * Places a queen on the chessboard and continues to the first rank of the next
     * file. If the puzzle is solved, returns true otherwise false.
     * 
     * @param pos the current position on the chessboard.
     * 
     * @return whether the puzzle is complete.
     */
    boolean place(Square pos) {
        board.putDown(new Square(pos.getFile(), pos.getRank()));
        pos.setFile(pos.getFile() + 1);
        pos.setRank(0);

        return board.size() == board.getQueens()
                                    .size();
    }
}

package com.selesy.nqueens.solvers.backtracking;

import java.util.List;
import java.util.Optional;

import com.selesy.nqueens.board.Square;
import com.selesy.nqueens.solvers.Solver;

public class BacktrackingSolver implements Solver {

    BacktrackingBoard board;

    public BacktrackingSolver(int size) {
        board = new BacktrackingBoard(size);
        // System.out.println("Board size: " + board.size());
    }

    public Optional<List<Square>> solve() {
        char file = 0;
        int rank = 0;

        do {
            Square s = new Square(file, rank);

            // System.out.print("Square: " + s);

            // if there's a valid square in this file, fill it and start
            // at rank 0 on the next file
            if (board.isValid(s)) {
                board.putDown(s);

                // System.out.println(", Queens: " + board.queens);

                // if there's a queen in each file then we're done
                if (board.getQueens()
                         .size() >= board.size()) {
                    break;
                }

                // otherwise start at the lowest rank of the next file
                file++;
                rank = 0;
                continue;
            }

            // System.out.println(", Violations: " + board.violations(s));

            // try the next square (forward)
            rank++;

            // at the last rank for the file then (recursively) backtrack
            if (rank >= board.size()) {
                Square bt = backtrack(board.pickUp());
                file = (char) bt.getFile();
                rank = bt.getRank();
            }

            // after backtracking as far as possible, the file will be
            // zero (a) and the rank will be equal to the size of the
            // board so no additional positions are possible.
        } while (rank < board.size());

        // System.out.println("----------------------------------------");

        return Optional.of(board.getQueens());
    }

    /**
     * Recursively backtracks through the already placed queens trying to find one
     * that can still have its rank increased without exceeding the boundaries of
     * the board. If the square's file is zero, no more recursion is possible so
     * that square is returned with an invalid rank (indicating there's no
     * solution).
     * 
     * @param square
     *                   the next candidate square.
     * @return the next valid square to try.
     */
    Square backtrack(Square square) {
        // If the square is itself in the highest rank, try the next one
        if (square.getRank() >= board.size() - 1 && square.getFile() > 0) {
            return backtrack(board.pickUp());
        }

        // Once a available square is found, increase its rank
        square.setRank(square.getRank() + 1);
        return square;
    }
}
package com.selesy.nqueens.board;

/**
 * Methods that implemented boards must provide.
 */
public interface Board {

    /**
     * Places the provided Square on the chessboard.
     * 
     * @param square
     *                   the square.
     */
    void putDown(Square square);

    /**
     * Picks up the most recently placed piece.
     * 
     * @return the square.
     */
    Square pickUp();

    /**
     * Picks up the square matching the parameter.
     * 
     * @param square
     *                   the piece.
     */
    void pickUp(Square square);

}

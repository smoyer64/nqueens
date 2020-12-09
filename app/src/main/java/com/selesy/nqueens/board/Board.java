package com.selesy.nqueens.board;

import java.util.List;

/**
 * Methods that implemented boards must provide.
 */
public interface Board {

    /**
     * Removes all pieces from the chessboard.
     */
    void clear();

    /**
     * Loads the provided list of squares onto the chessboard.
     * 
     * @param positions
     *                      the list of squares.
     */
    void load(List<Square> positions);

    /**
     * Loads the provided list of positions onto the board where positions are a
     * comma-separated list of algebraic notation character pairs such as "a1, b2,
     * c3, d4".
     * 
     * @param positions
     *                      the positions.
     */
    void load(String positions);

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

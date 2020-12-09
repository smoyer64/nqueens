package com.selesy.nqueens.board;

/**
 * Provides the data structures needed to represent an arbitrary-sized
 * chessboard (up to 26x26 squares in size) as well as the position of the white
 * and black players' pieces.
 */
public class BaseBoard {

    // the default dimensions for a chessboard is 8x8
    public static final int DEFAULT_DIMENSIONS = 8;

    // the minimum and maximum size allowed for these solvers based
    // primarily on the ease of converting positions to algebraic
    // notation.
    public static final int MIN_DIMENSIONS = 1;
    public static final int MAX_DIMENSIONS = 26;

}

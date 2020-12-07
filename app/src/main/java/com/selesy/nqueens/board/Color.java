package com.selesy.nqueens.board;

/**
 * Color represents the checkered squares on a chessboard as well as
 * the color of the two player's pieces.
 */
public enum Color {
    // WARNING: changing the order of these members will cause the
    // lower-left square on the chessboard to be black instead of
    // white.  It will also cause black's pieces to start on the
    // lower ranks.
    BLACK, WHITE;
}

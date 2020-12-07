package com.selesy.nqueens.board;

import lombok.Data;

/**
 * Represents a Square on a chess Board as a zero-indexed pair of file and rank
 * as required for algebraic notation. Methods are provided to translate this
 * type to and from it's String representation. On a standard 8x8 chess board,
 * 0,0 is the integer coordinates of the lower-left square named "a1". Likewise,
 * the coordinates 7,7 correspond to the upper-right square named "h8".
 */
@Data
public class Square {

    static final String MESSAGE_FILE_RANK_INDEX = "Both file and rank should be in the range 0-25 inclusive";
    static final String MESSAGE_NOTATION_FORMAT = "Notation should be a two-character string with one lower-case letter followed by a number";

    /**
     * Represents the integer offset required to convert a file between a zero-based
     * index and algebraic notation.
     */
    public static final int MIN_FILE = 'a';

    /**
     * Represents the integer offset required to convert a rank between a zero-based
     * index and algebraic notation.
     */
    public static final int MIN_RANK = 1;

    // The zero-based column (x) position.
    int file;

    // The zero-based row (y) position.
    int rank;

    /**
     * Creates a new Square given the zero-based file and rank coordinates.
     * 
     * @param file
     *                 the column/x position of the Square.
     * @param rank
     *                 the row/x position of the Square.
     * @throws IllegalArgumentException
     *                                      when either the file or rank is outside
     *                                      the range 0-25 inclusive.
     */
    public Square(int file, int rank) throws IllegalArgumentException {
        if (file < 0 || file >= Board.MAX_DIMENSIONS || rank < 0 || rank >= Board.MAX_DIMENSIONS) {
            StringBuilder sb = new StringBuilder();
            sb.append(MESSAGE_FILE_RANK_INDEX);
            sb.append(" - File: ");
            sb.append(file);
            sb.append(", Rank: ");
            sb.append(rank);
            throw new IllegalArgumentException(sb.toString());
        }
        this.file = file;
        this.rank = rank;
    }

    /**
     * Creates a new Square with coordinates calculated from the specified algebraic
     * notation.
     * 
     * @param notation
     *                     the algebraic notation of the position.
     * @return a new Square with the specified coordinates.
     * @throws IllegalArgumentException
     *                                      if the provided notation is not a
     *                                      two-character string or if either the
     *                                      calculated file or rank falls outside
     *                                      the range 0-25 inclusive.
     */
    public static Square fromString(String notation) throws IllegalArgumentException {
        if (notation.length() < 2) {
            String msg = badNotationMessage(notation);
            throw new IllegalArgumentException(msg);
        }
        char file = (char) (notation.charAt(0) - MIN_FILE);
        int rank = 0;
        try {
            rank = Integer.parseInt(notation.substring(1));
            rank -= MIN_RANK;
        } catch (NumberFormatException e) {
            String msg = badNotationMessage(notation);
            throw new IllegalArgumentException(msg, e);
        }
        return new Square(file, rank);
    }

    /**
     * Returns the position of this square as a String in algebraic notation.
     * 
     * @return the coordinates of this Square.
     */
    public String toString() {
        return (new StringBuilder()).append((char) (file + MIN_FILE))
                                    .append(rank + MIN_RANK)
                                    .toString();
    }

    static String badNotationMessage(String notation) {
        return new StringBuilder().append(MESSAGE_NOTATION_FORMAT)
                                  .append(" - Notation: ")
                                  .append(notation)
                                  .toString();
    }
}

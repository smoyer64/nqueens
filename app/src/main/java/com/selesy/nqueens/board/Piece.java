package com.selesy.nqueens.board;

/**
 * Enumerates the types of pieces that are played during a chess game including
 * their abbreviation, name and value. The omit field indicates whether the
 * piece's abbreviation should be shown in algebraic notation.
 * 
 * Note the the king's value is a place-holder only - it's set to zero so that
 * summing the pieces on the board can be accomplished easily.
 * 
 * @see https://www.chess.com/terms/chess-piece-value
 * @see https://www.chess.com/article/view/chess-notation
 * @see https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
 */
public enum Piece {
    // @formatter:off
    KING('K', "King", '\u2654', true, 0), // value is a place-holder only
    QUEEN('Q', "Queen", '\u2655', true, 9), //
    ROOK('R', "Rook", '\u2656', true, 5),
    BISHOP('B', "Bishop", '\u2657', true, 5),
    KNIGHT('N', "Knight", '\u2658', true, 3),
    PAWN('P', "Pawn", '\u2659', false, 1);
    // @formatter:on

    char abbrev;
    boolean displayed;
    String name;
    char symbol;
    int value;

    private Piece(char abbrev, String name, char symbol, boolean displayed, int value) {
        this.abbrev = abbrev;
        this.name = name;
        this.symbol = symbol;
        this.displayed = displayed;
        this.value = value;
    }

    /**
     * Provides the standard abbreviation for the piece in algebraic notation.
     * 
     * @return a character abbreviation for the piece.
     */
    public char getAbbreviation() {
        return abbrev;
    }

    /**
     * Provides a boolean indicating whether the piece's abbreviation is used when
     * specifying moves in algebraic notation.
     * 
     * @return whether the piece should be displayed.
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * Provides the standard name for the piece.
     * 
     * @return the piece's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the unicode symbol for this piece in the color of the passed
     * parameter.
     * 
     * @param color
     *                  the piece's color.
     * @return the unicode symbol.
     */
    public char getSymbol(Color color) {
        return (char) (symbol + (((color.ordinal() ^ 1) * 6)));
    }

    /**
     * Provides the point value for each piece so that the "score" of a game can be
     * determined from the pieces on the chessboard.
     * 
     * @return the piece's point value.
     */
    public int getValue() {
        return value;
    }

}

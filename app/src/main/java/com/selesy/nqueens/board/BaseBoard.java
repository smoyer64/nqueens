package com.selesy.nqueens.board;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides the data structures needed to represent an arbitrary-sized
 * chessboard (up to 26x26 squares in size) as well as the position of the white
 * and black players' pieces.
 */
public class BaseBoard {

    public static final int DEFAULT_DIMENSIONS = 8;
    public static final int MAX_DIMENSIONS = 26;

    // // Dimensions of the NxN board.
    // int n;

    // // Stores positions occupied by each player's pieces.
    // Map<Color, Map<Square, Piece>> pieces = new HashMap<>();

    // /**
    // * Creates a default 8x8 chessboard.
    // */
    // public BaseBoard() {
    // this(BaseBoard.DEFAULT_DIMENSIONS);
    // }

    // /**
    // * Creates an arbitrary NxN chessboard.
    // *
    // * @param n
    // * the number of files and ranks.
    // */
    // public BaseBoard(int n) {
    // this.n = n;
    // pieces.put(Color.BLACK, new HashMap<>());
    // pieces.put(Color.WHITE, new HashMap<>());
    // }

    // /**
    // * Calculates the color of the square based on its rank and file taking the
    // size
    // * of the board into consideration. For the traditional 8x8 board, a1
    // * (rank/file) square will be black and the right-most (from the white
    // player's
    // * perspective) square at a8 will be black. For odd-sized boards, both a1 and
    // * the rightmost square will be white.
    // *
    // * @param square
    // * the square.
    // * @return the color of the square.
    // */
    // public Color getColor(Square square) {
    // // int odd = (boardSize + (int) (rank - FIRST_RANK) - FIRST_FILE) & 1;
    // // if (((file - FIRST_FILE) & odd) == 1) {
    // // return Color.BLACK;
    // // }

    // return Color.WHITE;
    // }

    // /**
    // * Calculates the color of the square based on its rank and file taking the
    // size
    // * of the board into consideration. For the traditional 8x8 board, a1
    // * (rank/file) square will be black and the right-most (from the white
    // player's
    // * perspective) square at a8 will be black. For odd-sized boards, both a1 and
    // * the rightmost square will be white.
    // *
    // * @param file
    // * the column/x coordinate.
    // * @param rank
    // * the row/y coordinate.
    // * @return the color of the square.
    // */
    // public Color getColor(char file, int rank) {
    // return this.getColor(new Square(file, rank));
    // }

    // public void load() {

    // }

    // void loadPawns(Color color, int rank) {
    // for (int i = 0; i < 8; i++) {

    // }
    // }

    // public void pickUp(Color color, Piece piece, Square square) {

    // }

    // public Map.Entry<Color, Piece> pickUp(Square square) {
    // return null; // TODO
    // }

    // public void putDown(Color color, Piece piece, Square square) {
    // pieces.get(color)
    // .put(square, piece);
    // }

    // /**
    // * Calculates the score for each player based solely on the pieces that remain
    // * on the chessboard.
    // *
    // * @return the scores.
    // */
    // public Map<Color, Integer> score() {
    // return pieces.entrySet()
    // .stream()
    // .collect(Collectors.toMap(e -> e.getKey(), this::colorScore, (l, r) -> l +
    // r));
    // }

    // int colorScore(Map.Entry<Color, Map<Square, Piece>> e) {
    // return e.getValue()
    // .values()
    // .stream()
    // .mapToInt(v -> v.getValue())
    // .sum();
    // }

    // /**
    // * Returns the size of this (NxN) chessboard.
    // *
    // * @return the number of files and ranks.
    // */
    // public int size() {
    // return n;
    // }

    // public String toString() {

    // return ""; // TODO
    // }

}

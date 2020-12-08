package com.selesy.nqueens.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Piece.java")
public class PieceTests {

    /*
     * Verifies that the private constructor and accessors are wired up correctly.
     */
    @Test
    void testConstructorAndAccessors() {
        assertEquals("Rook", Piece.ROOK.getName());
        assertEquals('R', Piece.ROOK.getAbbreviation());
        assertEquals('\u265c', Piece.ROOK.getSymbol(Color.BLACK));
        assertEquals(5, Piece.ROOK.getValue());
        assertTrue(Piece.ROOK.isDisplayed());

        assertFalse(Piece.PAWN.isDisplayed());
    }

}

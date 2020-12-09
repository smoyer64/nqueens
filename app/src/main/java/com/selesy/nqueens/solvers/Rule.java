package com.selesy.nqueens.solvers;

/**
 * An enumeration describing the different types of placement violations that
 * might happen. Vertical, horizontal and diagonal are the typical n-queens
 * rules and triplet is the additional constraint included for this code
 * challenge.
 * 
 * @see https://en.wikipedia.org/wiki/Eight_queens_puzzle
 * @see https://en.wikipedia.org/wiki/No-three-in-line_problem
 */
public enum Rule {

    VERTICAL, HORIZONTAL, DIAGONAL, TRIPLET;

    /**
     * Creates a pretty version of the Rule's name where only the first letter is
     * capitalized.
     * 
     * @return the pretty name.
     */
    public String toString() {
        return name().substring(0, 1) + name().substring(1)
                                              .toLowerCase();
    }

}

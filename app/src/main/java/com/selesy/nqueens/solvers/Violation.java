package com.selesy.nqueens.solvers;

import com.selesy.nqueens.board.Square;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Provides a data structure to store the details of a violation. This allows a
 * list of violations to be created between a specific queen (whether placed or
 * not) and the rest of the placed queens.
 */
@Data
@AllArgsConstructor
public class Violation {

    /*
     * Named as though in a Pair, this queen will generally have a lower index in
     * the list of placed queens.
     */
    Square leftQueen;

    /*
     * Named as though in a Pair, this queen will generally have a higher index in
     * the list of placed queens.
     */
    Square RightQueen;

    /*
     * Describes which error was violated - this is helped by the fact that for any
     * given queen pair, only one rule can be violated at a time.
     */
    Rule rule;
}

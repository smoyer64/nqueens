package com.selesy.nqueens.solvers;

import java.util.List;
import java.util.Optional;

import com.selesy.nqueens.board.Square;

/**
 * Methods that all solvers must implement
 */
public interface Solver {

    /**
     * Finds the solution targeted by the solver.
     * 
     * @return the solution as a list of squares containing queens if there is at
     *         least one solution. Returns an empty optional otherwise.
     */
    Optional<List<Square>> solve();
}

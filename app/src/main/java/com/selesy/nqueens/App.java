/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.selesy.nqueens;

import java.util.stream.IntStream;

import com.selesy.nqueens.solvers.Solver;
import com.selesy.nqueens.solvers.backtracking.BacktrackingSolver;

/**
 * Entry point for the N-Queens application.
 */
public class App { // TODO: add argument parsing to provide help and solver choice/configuration

    public static void main(String[] args) {
        // AnsiConsole.systemInstall();
        // System.out.println(ansi().fg(RED).bg(WHITE).a(new
        // App().getGreeting()).reset());
        // AnsiConsole.systemUninstall();
        (new App()).run();
    }

    void run() {
        // Print solutions to the first 18 N-Queens puzzles
        IntStream.range(1, 19)
                 .mapToObj(this::solve)
                 .forEach(System.out::println);

        // Start up the unfinished genetic solver
        // (new GeneticSolver(8)).solve();
    }

    String solve(int size) {
        Solver solver = new BacktrackingSolver(size);
        return solver.solve()
                     .map(s -> String.format("%2d - %s", size, s.toString()))
                     .orElse(String.format("%2d - No solution", size));
    }
}

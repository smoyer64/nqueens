/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.selesy.nqueens;

import java.util.stream.IntStream;

import com.selesy.nqueens.solvers.Solver;
import com.selesy.nqueens.solvers.backtracking.BacktrackingSolver;

public class App {

    public static void main(String[] args) {
        // AnsiConsole.systemInstall();
        // System.out.println(ansi().fg(RED).bg(WHITE).a(new
        // App().getGreeting()).reset());
        // AnsiConsole.systemUninstall();
        (new App()).run();
    }

    void run() {
        IntStream.range(1, 27)
                 .mapToObj(this::solve)
                 .forEach(System.out::println);
    }

    String solve(int size) {
        Solver solver = new BacktrackingSolver(size);
        return solver.solve()
                     .map(s -> String.format("%2d - %s", size, s.toString()))
                     .orElse(String.format("%2d - No solution", size));
    }
}

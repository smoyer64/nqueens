/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.selesy.nqueens;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        System.out.println(ansi().fg(RED).bg(WHITE).a(new App().getGreeting()).reset());
        AnsiConsole.systemUninstall();
    }
}

package com.diepoe;

import com.diepoe.securitease.SecuriTease;

import java.io.Console;

import com.cthiebaud.passwordvalidator.ValidationResult;

public class SecuriTeaseApp {
    public static void main(String[] args) {
        printWelcome();

        // Get console for hidden input
        Console console = System.console();
        if (console == null) {
            System.out.println("No console available. Please run this in a console.");
            return;
        }

        SecuriTease validator = new SecuriTease();

        while (true) {
            String password = console.readLine("Enter a password to validate (or type 'quit' to exit): ");

            // Check if the user wants to quit
            if ("quit".equalsIgnoreCase(password)) {
                System.out.println("Exiting the program.");
                break;
            }

            // Validate the password using the student's implementation
            ValidationResult result = validator.validate(password);

            // Display the result
            if (result.isValid()) {
                char[] repeatedPasswordArray = console.readPassword(
                        "Please repeat the password blindly (if your dumb brain can even remember it 🙄): ");
                String repeatedPassword = new String(repeatedPasswordArray);

                if (password.equals(repeatedPassword)) {
                    System.out.println(result.message());
                    break;
                } else {
                    System.out.println(
                            "[Exasperated sigh] Sweetie... how hard is it to type the same thing twice? Like, literally just copy what you did the first time? But nooo, here we are... Whenever you're ready to get it together, I'll be waiting. Try. Again. 💅");
                }
            } else {
                System.out.println(result.message());
            }
        }
    }

    public static void printWelcome() {
        final String RESET = "\033[0m";
        final String TRUE_PINK = "\033[38;2;255;20;147m";

        System.out.println(
                TRUE_PINK + "   _____ ______ _____ _    _ _____  _____ _______ ______          _____ ______ " + RESET);
        System.out.println(TRUE_PINK
                + "  / ____|  ____/ ____| |  | |  __ \\|_   _|__   __|  ____|   /\\   / ____|  ____|" + RESET);
        System.out.println(
                TRUE_PINK + " | (___ | |__ | |    | |  | | |__) | | |    | |  | |__     /  \\ | (___ | |__   " + RESET);
        System.out.println(TRUE_PINK
                + "  \\___ \\|  __|| |    | |  | |  _  /  | |    | |  |  __|   / /\\ \\ \\___ \\|  __|  " + RESET);
        System.out.println(TRUE_PINK
                + "  ____) | |___| |____| |__| | | \\ \\ _| |_   | |  | |____ / ____ \\____) | |____ " + RESET);
        System.out.println(TRUE_PINK
                + " |_____/|______\\_____||____/|_|  \\_\\_____|  |_|  |______/_/    \\_\\_____/|______|" + RESET);
        System.out.println();
    }
}
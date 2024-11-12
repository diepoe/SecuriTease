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
            char[] passwordArray = console.readPassword("Enter a password to validate (or type 'quit' to exit): ");
            String password = new String(passwordArray);

            // Check if the user wants to quit
            if ("quit".equalsIgnoreCase(password)) {
                System.out.println("Exiting the program.");
                break;
            }

            // Validate the password using the student's implementation
            ValidationResult result = validator.validate(password);

            // Display the result
            if (result.isValid()) {
                System.out.println(result.message());
                break;
            } else {
                System.out.println("Password is invalid: " + result.message());
            }
        }
    }

    public static void printWelcome() {
        System.out.println("   _____ ______ _____ _    _ _____  _____ _______ ______          _____ ______ ");
        System.out.println("  / ____|  ____/ ____| |  | |  __ \\|_   _|__   __|  ____|   /\\   / ____|  ____|");
        System.out.println(" | (___ | |__ | |    | |  | | |__) | | |    | |  | |__     /  \\ | (___ | |__   ");
        System.out.println("  \\___ \\|  __|| |    | |  | |  _  /  | |    | |  |  __|   / /\\ \\ \\___ \\|  __|  ");
        System.out.println("  ____) | |___| |____| |__| | | \\ \\ _| |_   | |  | |____ / ____ \\____) | |____ ");
        System.out.println(" |_____/|______\\_____||____/|_|  \\_\\_____|  |_|  |______/_/    \\_\\_____/|______|");
    }
}
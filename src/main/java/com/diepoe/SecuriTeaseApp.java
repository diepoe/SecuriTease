package com.diepoe;

import com.diepoe.securitease.SecuriTease;
import com.cthiebaud.passwordvalidator.ValidationResult;

public class SecuriTeaseApp {
    public static void main(String[] args) {
        printWelcome();

        SecuriTease validator = new SecuriTease();

        ValidationResult result = validator.validate("password");
        System.out.println(result.isValid());
        System.out.println(result.message());
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
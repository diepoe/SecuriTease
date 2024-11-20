package com.diepoe.securitease;

/**
 * Functional interface that represents a checking function that is used to
 * validate a password string
 * 
 * @author Dietrich Poensgen, Mikail Demirel
 */
@FunctionalInterface
public interface CheckingFunction {
    boolean check(String password, int threshold);
}
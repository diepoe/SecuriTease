package com.diepoe.securitease;

/**
 * @author Dietrich Poensgen, Mikail Demirel
 */
@FunctionalInterface
public interface CheckingFunction {
    boolean check(String password, int threshold);
}
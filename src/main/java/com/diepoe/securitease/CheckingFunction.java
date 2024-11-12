package com.diepoe.securitease;

@FunctionalInterface
public interface CheckingFunction {
    boolean check(String password, int threshold);
}
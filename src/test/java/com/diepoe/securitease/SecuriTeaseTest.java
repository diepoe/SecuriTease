package com.diepoe.securitease;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class SecuriTeaseTest {
    private SecuriTease securiTease;

    @BeforeEach
    void init() {
        securiTease = new SecuriTease();
    }

    @DisplayName("Test null password")
    @Test
    void testNullPassword() {
        String nullPassword = null;

        assertThrows(IllegalArgumentException.class, () -> securiTease.validate(nullPassword),
                "Null password should throw IllegalArgumentException");
    }

    @DisplayName("Test invalid password")
    @Test
    void testInvalidPassword() {
        String invalidPassword = "pasword"; // too short

        String[] want = { "Your password must contain at least 8 characters ⛔︎" };
        String got = securiTease.validate(invalidPassword).message();

        assertFalse(securiTease.validate(invalidPassword).isValid(), "Password is valid but should be invalid");
        assertTrue(Arrays.asList(want).contains(got),
                String.format("Invalid password message not found, got: %s", got));
    }

    @DisplayName("Test valid password")
    @Test
    void testValidPassword() {
        String validPassword = "42WagnerXXXXIItaly312//"; // a minimal valid password

        assertTrue(securiTease.validate(validPassword).isValid(), "Password is invalid but should be valid");
        assertEquals(
                "[Slow clap] Ohhh, congratulations, you finally managed to enter a valid password. Want a cookie for doing the absolute bare minimum? 🍪🙄",
                securiTease.validate(validPassword).message());
    }

    @DisplayName("Test CheckingFunction: roman literal sum")
    @Test
    void testCheckRomanLiteralSum() {
        String gotString = "IXasldfkjL"; // = 59
        int threshold = 59;

        boolean got = securiTease.checkRomanLiteralSum(gotString, threshold);
        assertTrue(got, "Roman literal isn't equal to threshold");
    }

    @DisplayName("Test CheckingFunction: contains European country")
    @Test
    void testCheckContainsEuropeanCountry() {
        String gotString = "Germany";
        int threshold = 1;

        boolean got = securiTease.checkContainsEuropeanCountry(gotString, threshold);
        assertTrue(got, "Error: string doesn't contain European country");
    }

    @DisplayName("Test CheckingFunction: contains composer")
    @Test
    void testCheckContainsComposer() {
        String gotString = "Bach";
        int threshold = 1;

        boolean got = securiTease.checkContainsComposer(gotString, threshold);
        assertTrue(got, "Error: string doesn't contain composer");
    }

    @DisplayName("Test CheckingFunction: meaning of life")
    @Test
    void testCheckMeaningOfLife() {
        String gotString = "42";
        int threshold = 42;

        boolean got = securiTease.checkMeaningOfLife(gotString, threshold);
        assertTrue(got, "Error: string doesn't contain \"42\"");
    }

    @DisplayName("Test CheckingFunction: Special Characters")
    @Test
    void testCheckSpecialCharacter() {
        String gotString = "@/);";
        int threshold = 4;

        boolean got = securiTease.checkSpecialCharacters(gotString, threshold);
        assertTrue(got, "Error: string doesn't contain Special Character");
    }

    @DisplayName("Test CheckingFunction: Eiffel Tower Height")
    @Test
    void testCheckEiffelTowerHeight() {
        String gotString = "312";
        int threshold = 1;

        boolean got = securiTease.checkEiffelTowerHeight(gotString, threshold);
        assertTrue(got, "Error: string doesn't contain the height of the Eiffel tower");
    }
}

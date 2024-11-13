import com.cthiebaud.passwordvalidator.ValidationResult;
import com.diepoe.securitease.SecuriTease;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecuriTeaseTest {
    @Test
    public void testInvalidPassword() {
        SecuriTease securiTease = new SecuriTease(true);
        assertEquals(false, securiTease.validate("password").isValid());
    }

    @Test
    public void testValidPassword() {
        SecuriTease securiTease = new SecuriTease(true);
        assertEquals(true, securiTease.validate("42WagnerXXXXIItaly312").isValid());
    }
}

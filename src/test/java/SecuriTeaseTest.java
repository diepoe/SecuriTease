import com.cthiebaud.passwordvalidator.ValidationResult;
import com.diepoe.securitease.SecuriTease;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecuriTeaseTest {
    @Test
    public void test() {
        SecuriTease securiTease = new SecuriTease();
        assertEquals(false, securiTease.validate("password").isValid());
    }
}

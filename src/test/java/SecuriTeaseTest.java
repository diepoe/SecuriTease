import com.diepoe.securitease.SecuriTease;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SecuriTeaseTest {
    private SecuriTease securiTease;

    @BeforeEach
    public void init() {
        securiTease = new SecuriTease();
    }

    @Test
    public void testInvalidPassword() {
        String invalidPassword = "pasword"; // too short

        String[] want = {
                "[Rolls eyes dramatically] Listen honey, if you think I'm gonna accept your sad little short-as-a-stinky-fart password, you've got another thing coming. Make it 8 characters or more - I don't make the rules... oh wait, yes I do. 💅✨",
                "[Augenrollen] Hör mal, Schnegge, wenn du denkst, dass ich deinen Furz von Passwort akzeptiere, dann hast du dich geschnitten. 8 Buchstaben in deiner hässlichen Handschrift oder mehr - ich mache die Regeln nicht... oh warte, doch. 💅✨",
                "[Roule les yeux de façon dramatique] Écoute, chérie, si tu crois que je vais accepter ton petit mot de passe aussi court qu'un pet qui pue, tu te trompes. Mets 8 caractères ou plus - ce n'est pas moi qui fais les règles... oh attends, si je les fais. 💅✨" };
        String got = securiTease.validate(invalidPassword).message();

        assertFalse(securiTease.validate(invalidPassword).isValid(), "Password is valid but should be invalid");
        assertTrue(Arrays.asList(want).contains(got),
                String.format("Invalid password message not found, got: %s", got));
    }

    @Test
    public void testValidPassword() {
        String validPassword = "42WagnerXXXXIItaly312"; // a minimal valid password

        assertTrue(securiTease.validate(validPassword).isValid(), "Password is invalid but should be valid");
        assertEquals(
                "[Slow clap] Ohhh, congratulations, you finally managed to enter a valid password. Want a cookie for doing the absolute bare minimum? 🙄",
                securiTease.validate(validPassword).message());
    }
}

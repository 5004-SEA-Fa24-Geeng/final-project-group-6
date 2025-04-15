import org.junit.jupiter.api.Test;
import util.InputValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInputValidator {

    @Test
    public void testValid10DigitISBN() {
        assertTrue(InputValidator.isValidISBN("1234567890"));
    }

    @Test
    public void testValid13DigitISBN() {
        assertTrue(InputValidator.isValidISBN("1234567890123"));
    }

    @Test
    public void testInvalidShortISBN() {
        assertFalse(InputValidator.isValidISBN("12345"));
    }

    @Test
    public void testInvalidLongISBN() {
        assertFalse(InputValidator.isValidISBN("12345678901234"));
    }

    @Test
    public void testISBNWithLetters() {
        assertFalse(InputValidator.isValidISBN("12345ABCDX"));
    }

    @Test
    public void testEmptyISBN() {
        assertFalse(InputValidator.isValidISBN(""));
    }
}

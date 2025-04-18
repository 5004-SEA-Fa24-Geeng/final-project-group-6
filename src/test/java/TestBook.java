import model.BookData;
import model.IBook;
import model.IllegalBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestBook {

    private IBook standardBook1;
    private IBook standardBook2;
    private IBook sameISBNStandard;
    private IBook illegalBook;

    @BeforeEach
    public void setup() {
        standardBook1 = new StandardBook("1234567890", "Test Title", "Author A", "Fiction", "available");
        standardBook2 = new StandardBook("9876543210", "Another Title", "Author B", "Science", "unavailable");
        sameISBNStandard = new StandardBook("1234567890", "Different Title", "Author C", "Drama", "unavailable");

        illegalBook = new IllegalBook("1111111111", "Illegal Book", "Author X", "Restricted", "available");

    }

    @Test
    public void testGetters_StandardBook() {
        assertEquals("1234567890", standardBook1.getISBN());
        assertEquals("Test Title", standardBook1.getBookTitle());
        assertEquals("Author A", standardBook1.getAuthor());
        assertEquals("Fiction", standardBook1.getCategory());
        assertEquals("STANDARD", standardBook1.getBookType());
        assertEquals("available", standardBook1.getStatus());
    }

    @Test
    public void testGetters_IllegalBook() {
        assertEquals("1111111111", illegalBook.getISBN());
        assertEquals("Illegal Book", illegalBook.getBookTitle());
        assertEquals("Author X", illegalBook.getAuthor());
        assertEquals("Restricted", illegalBook.getCategory());
        assertEquals("ILLEGAL", illegalBook.getBookType());
        assertEquals("available", illegalBook.getStatus());
    }

    @Test
    public void testSetStatus() {
        standardBook1.setStatus("unavailable");
        assertEquals("unavailable", standardBook1.getStatus());
    }

    @Test
    public void testToStringFormat() {
        String expected = "Test Title by Author A (Fiction) — ISBN: 1234567890 - available";
        assertEquals(expected, standardBook1.toString());
    }

    @Test
    public void testToStringWithInfoDelegatesToToString() {
        assertEquals(standardBook1.toString(), standardBook1.toStringWithInfo(BookData.CATEGORY));
    }

    @Test
    public void testEqualsByISBN() {
        assertEquals(standardBook1, sameISBNStandard);
        assertNotEquals(standardBook1, standardBook2);
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(standardBook1.hashCode(), sameISBNStandard.hashCode());
    }

    @Test
    public void testCompareToByISBN() {
        assertTrue(standardBook1.compareTo(standardBook2) < 0);
        assertEquals(0, standardBook1.compareTo(sameISBNStandard));
    }

    @Test
    public void testIllegalBook_getReplacement() {
        IBook illegal = new IllegalBook("1111111111", "Forbidden Knowledge", "X", "Secret", "available");

        StandardBook replacement = ((IllegalBook) illegal).getReplacement();

        assertNotNull(replacement);
        assertEquals("UNKNOWN", replacement.getBookTitle());
        assertEquals("J.R", replacement.getAuthor());
        assertEquals("available", replacement.getStatus());
        assertEquals("STANDARD", replacement.getBookType());
    }

    @Test
    public void testIllegalBook_validPassword() {
        IllegalBook illegal = new IllegalBook("1111111111", "Dangerous Book", "X", "Restricted", "available");
        assertTrue(illegal.validatePassword("secret"));
    }

    @Test
    public void testIllegalBook_invalidPassword() {
        IllegalBook illegal = new IllegalBook("1111111111", "Dangerous Book", "X", "Restricted", "available");
        assertFalse(illegal.validatePassword("wrongpassword"));
    }

}

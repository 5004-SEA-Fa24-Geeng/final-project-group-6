import model.*;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestBookLoader {

    @Test
    public void testLoadBooksFromFile() {
        Set<IBook> books = BookLoader.loadBooksFromFile("/Library.csv");

        assertNotNull(books);
        assertEquals(4, books.size());

        boolean hasStandard = books.stream().anyMatch(book -> book instanceof StandardBook);
        boolean hasIllegal = books.stream().anyMatch(book -> book instanceof IllegalBook);

        assertTrue(hasStandard);
        assertTrue(hasIllegal);

    }

    @Test
    public void testToBookCallsStandardBook(){
        String line = "123456,STANDARD,Short,Title,Jane,Category,Available";
        Map<BookData, Integer> columnMap = Map.of(BookData.ISBN, 0,
                BookData.TYPE, 1,
                BookData.NAME, 2,
                BookData.TITLE, 3,
                BookData.AUTHOR, 4,
                BookData.CATEGORY, 5,
                BookData.STATUS, 6
        );

        IBook book = BookLoader.toBook(line, columnMap);
        assertNotNull(book);
        assertTrue(book instanceof StandardBook);

    }

    @Test
    public void testToBookCallsIllegalBook(){
        String line = "123456,ILLEGAL,Short,Title,Jane,Category,Available";
        Map<BookData, Integer> columnMap = Map.of(BookData.ISBN, 0,
                BookData.TYPE, 1,
                BookData.NAME, 2,
                BookData.TITLE, 3,
                BookData.AUTHOR, 4,
                BookData.CATEGORY, 5,
                BookData.STATUS, 6
        );

        IBook book = BookLoader.toBook(line, columnMap);
        assertNotNull(book);
        assertTrue(book instanceof IllegalBook);

    }
}

import controller.ISorts;
import controller.Sorts;
import model.BookData;
import model.IBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SortsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSortByTitleAsc() {
        ISorts sorts = new Sorts();
        List<IBook> books = Arrays.asList(new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available"), new StandardBook("12345678911", "titlesame",
                "authorsame", "comic", "available"), new StandardBook("12345678912", "titlezame",
                "authorzame", "comic", "available"));
        books.sort(sorts.getSorting(BookData.TITLE, true));

        assertEquals("titlename", books.get(0).getBookTitle());
        assertEquals("titlesame", books.get(1).getBookTitle());
        assertEquals("titlezame", books.get(2).getBookTitle());

    }

    @Test
    void testSortByTitleDesc() {
        ISorts sorts = new Sorts();
        List<IBook> books = Arrays.asList(new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available"), new StandardBook("12345678911", "titlesame",
                "authorsame", "comic", "available"), new StandardBook("12345678912", "titlezame",
                "authorzame", "comic", "available"));
        books.sort(sorts.getSorting(BookData.TITLE, false));

        assertEquals("titlezame", books.get(0).getBookTitle());
        assertEquals("titlesame", books.get(1).getBookTitle());
        assertEquals("titlename", books.get(2).getBookTitle());

    }

    @Test
    void testSortByAuthorAsc() {
        ISorts sorts = new Sorts();
        List<IBook> books = Arrays.asList(new StandardBook("12345678910", "titlename",
                "Jane doe", "comic", "available"), new StandardBook("12345678911", "titlesame",
                "John doe", "comic", "available"), new StandardBook("12345678912", "titlezame",
                "bob roberts", "comic", "available"));
        books.sort(sorts.getSorting(BookData.AUTHOR, true));

        assertEquals("bob roberts", books.get(0).getAuthor());
        assertEquals("Jane doe", books.get(1).getAuthor());
        assertEquals("John doe", books.get(2).getAuthor());

    }

    @Test
    void testSortByAuthorDesc() {
        ISorts sorts = new Sorts();
        List<IBook> books = Arrays.asList(new StandardBook("12345678910", "titlename",
                "Jane doe", "comic", "available"), new StandardBook("12345678911", "titlesame",
                "John doe", "comic", "available"), new StandardBook("12345678912", "titlezame",
                "bob roberts", "comic", "available"));
        books.sort(sorts.getSorting(BookData.AUTHOR, false));

        assertEquals("John doe", books.get(0).getAuthor());
        assertEquals("Jane doe", books.get(1).getAuthor());
        assertEquals("bob roberts", books.get(2).getAuthor());

    }
}

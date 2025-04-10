import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void filter() {
        InsertBookClass comicBook = new InsertBookClass();
        boolean actual = Filters.filter(comicBook, BookDataSabrina.CATEGORY, Operations.EQUALS, "Comic");
        assertEquals(true, actual);

        boolean actual2 = Filters.filter(comicBook, BookDataSabrina.AUTHOR, Operations.EQUALS, "Author name");
        assertEquals(true, actual2);

        boolean actual3 = Filters.filter(comicBook, BookDataSabrina.TITLE, Operations.EQUALS, "Title name");
        assertEquals(true, actual3);

        boolean actual4 = Filters.filter(comicBook, BookDataSabrina.ISBN, Operations.EQUALS, "ISBN number");
        assertEquals(true, actual4);

        InsertBookClass historyBook = new InsertBookClass();
        boolean actual5 = Filters.filter(historyBook, BookDataSabrina.CATEGORY, Operations.EQUALS, "comic");
        assertEquals(false, actual5);


    }
}
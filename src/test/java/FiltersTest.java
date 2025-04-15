import controller.Filters;
import controller.IFilters;
import controller.Operations;
import model.BookData;
import model.IBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void filter() {
        //Doesn't pass currently due to issure in
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("123", "title", "authorname", "comic", "available");
        boolean actual = myFilter.filter(comicBook, BookData.CATEGORY, Operations.EQUALS, "Comic");
        assertEquals(true, actual);

        boolean actual2 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.EQUALS, "Author name");
        assertEquals(true, actual2);

        boolean actual3 = myFilter.filter(comicBook, BookData.TITLE, Operations.EQUALS, "Title name");
        assertEquals(true, actual3);

        boolean actual4 = myFilter.filter(comicBook, BookData.ISBN, Operations.EQUALS, "ISBN number");
        assertEquals(true, actual4);

        IBook historyBook = new StandardBook("123", "title", "authorname", "HISTORY", "available");
        boolean actual5 = myFilter.filter(historyBook, BookData.CATEGORY, Operations.EQUALS, "comic");
        assertEquals(false, actual5);


    }

    @Test
    public void testFilterInt() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "title", "authorname", "comic", "available");
        boolean actual1 = myFilter.filterInteger(1234567891, Operations.EQUALS, "1234567891");
        boolean actual2 = myFilter.filterInteger(1234567891, Operations.CONTAINS, "123456789");

        assertEquals(true, actual1);
        assertEquals(true, actual2);

    }

    @Test
    public void testFilterString() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "title", "authorname", "comic", "available");
        boolean actual1 = myFilter.filterString("title", Operations.EQUALS, "title");
        boolean actual2 = myFilter.filterString("title", Operations.CONTAINS, "le");
        assertEquals(true, actual1);
        assertEquals(true, actual2);

    }
}
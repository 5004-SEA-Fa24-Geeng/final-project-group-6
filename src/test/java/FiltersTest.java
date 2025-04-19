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

    @Test
    void filterCategory() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available");
        boolean actual = myFilter.filter(comicBook, BookData.CATEGORY, Operations.CONTAINS, "mic");
        assertEquals(true, actual);

        boolean actual2 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.CONTAINS, "Comic");
        assertEquals(true, actual2);

        boolean actual3 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.CONTAINS, "nonfiction");
        assertEquals(false, actual3);

        boolean actual4 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.EQUALS, "ComiC");
        assertEquals(true, actual4);

        boolean actual5 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.EQUALS, "Co miC");
        assertEquals(true, actual5);

        boolean actual6 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.EQUALS, "Comi");
        assertEquals(false, actual6);

        boolean actual7 = myFilter.filter(comicBook, BookData.CATEGORY, Operations.EQUALS, "fiction");
        assertEquals(false, actual7);
    }

    @Test
    void filterTitle() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available");

        boolean actual = myFilter.filter(comicBook, BookData.TITLE, Operations.CONTAINS, "tle");
        assertEquals(true, actual);

        boolean actual2 = myFilter.filter(comicBook, BookData.TITLE, Operations.CONTAINS, "TITLE");
        assertEquals(true, actual2);

        boolean actual3 = myFilter.filter(comicBook, BookData.TITLE, Operations.CONTAINS, "authoR");
        assertEquals(false, actual3);

        boolean actual4 = myFilter.filter(comicBook, BookData.TITLE, Operations.EQUALS, "TitleNAME");
        assertEquals(true, actual4);

        boolean actual5 = myFilter.filter(comicBook, BookData.TITLE, Operations.EQUALS, "Title NAME");
        assertEquals(true, actual5);

        boolean actual6 = myFilter.filter(comicBook, BookData.TITLE, Operations.EQUALS, "Title");
        assertEquals(false, actual6);

        boolean actual7 = myFilter.filter(comicBook, BookData.TITLE, Operations.EQUALS, "author");
        assertEquals(false, actual7);


    }

    @Test
    void filterAuthor() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available");

        boolean actual = myFilter.filter(comicBook, BookData.AUTHOR, Operations.CONTAINS, "naMe");
        assertEquals(true, actual);

        boolean actual2 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.CONTAINS, "author name");
        assertEquals(true, actual2);

        boolean actual3 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.CONTAINS, "title");
        assertEquals(false, actual3);

        boolean actual4 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.EQUALS, "Authorname");
        assertEquals(true, actual4);

        boolean actual5 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.EQUALS, "Author name");
        assertEquals(true, actual5);

        boolean actual6 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.EQUALS, "title");
        assertEquals(false, actual6);

        boolean actual7 = myFilter.filter(comicBook, BookData.AUTHOR, Operations.EQUALS, "Author");
        assertEquals(false, actual7);

    }

    @Test
    void filterISBN() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "titlename",
                "authorname", "comic", "available");

        boolean actual = myFilter.filter(comicBook, BookData.ISBN, Operations.CONTAINS, "12345678910");
        assertEquals(true, actual);

        boolean actual2 = myFilter.filter(comicBook, BookData.ISBN, Operations.CONTAINS, "123");
        assertEquals(true, actual2);

        boolean actual3 = myFilter.filter(comicBook, BookData.ISBN, Operations.CONTAINS, "321");
        assertEquals(false, actual3);

        boolean actual4 = myFilter.filter(comicBook, BookData.ISBN, Operations.EQUALS, "12345678910");
        assertEquals(true, actual4);

        boolean actual5 = myFilter.filter(comicBook, BookData.ISBN, Operations.EQUALS, "123456 78910");
        assertEquals(true, actual5);

        boolean actual6 = myFilter.filter(comicBook, BookData.ISBN, Operations.EQUALS, "123");
        assertEquals(false, actual6);

        boolean actual7 = myFilter.filter(comicBook, BookData.ISBN, Operations.EQUALS, "author");
        assertEquals(false, actual7);
    }


    @Test
    public void testFilterString() {
        IFilters myFilter = new Filters();
        IBook comicBook = new StandardBook("12345678910", "title",
                "authorname", "comic", "available");
        boolean actual1 = myFilter.filterString("title", Operations.EQUALS, "title");
        boolean actual2 = myFilter.filterString("title", Operations.CONTAINS, "le");
        assertEquals(true, actual1);
        assertEquals(true, actual2);

    }
}
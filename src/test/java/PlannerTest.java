import controller.IPlanner;
import controller.Planner;
import model.BookData;
import model.IBook;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    static Set<IBook> books;

    @BeforeEach
    public void setUp() {
        books = new HashSet<>();
        IBook book1 = new StandardBook("12345678910","The Great Book","Jane Doe","Fiction","Available");
        IBook book2 = new StandardBook("10987654321","The Bad Book","Jon Doe","NonFiction","Available");
        IBook book3 = new StandardBook("10987654320","The Bad Book 2","Jonny Doe","NonFiction","Available");

        books.add(book1);
        books.add(book2);
        books.add(book3);
    }

    @Test
    public void testFilter() {
        IPlanner planner = new Planner(books);
        List<IBook> filtered = planner.filter("ISBN==10987654321").toList();
        assertEquals(1, filtered.size());
        assertEquals("10987654321", filtered.get(0).getISBN());

        List<IBook> filtered2 = planner.filter("ISBN~=123").toList();
        assertEquals(1, filtered2.size());
        assertEquals("12345678910", filtered2.get(0).getISBN());

        List<IBook> filtered3 = planner.filter("ISBN 123").toList();
        assertEquals(3, filtered3.size());

        List<IBook> filtered4 = planner.filter("ISBN 123 456").toList();
        assertEquals(3, filtered4.size());
    }

    @Test
    public void testFilterSorting() {
        IPlanner planner = new Planner(books);
        List<IBook> filtered = planner.filter("bookTitle~=Book", BookData.AUTHOR).toList();
        assertEquals("Jane Doe", filtered.get(0).getAuthor());
        assertEquals("Jon Doe", filtered.get(1).getAuthor());
        assertEquals("Jonny Doe", filtered.get(2).getAuthor());
    }

    @Test
    public void testFilterSortingAscending() {
        IPlanner planner = new Planner(books);
        List<IBook> filtered = planner.filter("bookTitle~=Book", BookData.AUTHOR, true).toList();
        assertEquals("Jane Doe", filtered.get(0).getAuthor());
        assertEquals("Jon Doe", filtered.get(1).getAuthor());
        assertEquals("Jonny Doe", filtered.get(2).getAuthor());
    }

    @Test
    public void testFilterSortingDescending() {
        IPlanner planner = new Planner(books);
        List<IBook> filtered = planner.filter("bookTitle~=Book", BookData.AUTHOR, false).toList();
        assertEquals("Jonny Doe", filtered.get(0).getAuthor());
        assertEquals("Jon Doe", filtered.get(1).getAuthor());
        assertEquals("Jane Doe", filtered.get(2).getAuthor());
    }

}
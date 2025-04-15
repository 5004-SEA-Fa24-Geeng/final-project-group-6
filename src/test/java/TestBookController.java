import controller.BookController;
import controller.IBookController;
import model.BookList;
import model.IBook;
import model.IBookList;
import model.StandardBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBookController {

    private IBookController controller;
    private IBookList bookList;

    private IBook book1;
    private IBook book2;
    private IBook book3;

    @BeforeEach
    public void setup() {
        // Sample books
        book1 = new StandardBook(1234567890, "Book One", "Alice", "Fiction", "available");
        book2 = new StandardBook(9876543210L, "Book Two", "Bob", "Science", "available");
        book3 = new StandardBook(1111111111, "Book Three", "Charlie", "Drama", "unavailable");

        Set<IBook> books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);

        bookList = new BookList(books);
        controller = new BookController(bookList, new Filters(), new Sorts());
    }

    @Test
    public void testSuccessfulCheckOut() {
        controller.checkOutBooks("1234567890");
        assertEquals("unavailable", book1.getStatus());
    }

    @Test
    public void testSuccessfulCheckIn() {
        controller.checkInBooks("1111111111");
        assertEquals("available", book3.getStatus());
    }

    @Test
    public void testCheckOutUnavailableBook() {
        controller.checkOutBooks("1111111111"); // already unavailable
        assertEquals("unavailable", book3.getStatus());
    }

    @Test
    public void testCheckInAvailableBook() {
        controller.checkInBooks("1234567890"); // already available
        assertEquals("available", book1.getStatus());
    }

    @Test
    public void testInvalidISBNCheckOut() {
        controller.checkOutBooks("0000000000"); // not found
        assertEquals("available", book1.getStatus());
        assertEquals("available", book2.getStatus());
    }

    @Test
    public void testDisplayCheckoutList() {
        controller.checkOutBooks("1234567890");
        controller.checkOutBooks("9876543210");

        Set<IBook> checkedOut = bookList.getAllBooks();
        long count = checkedOut.stream().filter(book -> book.getStatus().equalsIgnoreCase("unavailable")).count();
        assertEquals(3, count); // including the initially unavailable book
    }
}
